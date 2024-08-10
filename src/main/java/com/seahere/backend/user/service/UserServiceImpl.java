package com.seahere.backend.user.service;

import com.seahere.backend.common.entity.Role;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.domain.UserStatus;
import com.seahere.backend.user.exception.BrokerPermissionException;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;
import com.seahere.backend.user.request.OAuthSignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signupCustomer(CustomerSignupReq customerSignupReq) {

        UserEntity user = customerSignupReq.toEntity();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signupBroker(BrokerSignupReq brokerSignupReq) {
        UserEntity user = brokerSignupReq.toEntity();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signupCeo(CeoSignupReq ceoSignupReq) {
        UserEntity user = ceoSignupReq.toEntity();
        user.passwordEncode(passwordEncoder);

        CompanyEntity company = companyRepository.findById(ceoSignupReq.getCompanyId())
                .orElseThrow(CompanyNotFound::new);
        user.updateCompany(company);

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signupOauth(OAuthSignupReq oauthSignupReq) {
        UserEntity user = userRepository.findById(oauthSignupReq.getUserId())
                .orElseThrow(UserNotFound::new);
        user.signupOAuth(oauthSignupReq.getUsername(),oauthSignupReq.getAddress(),oauthSignupReq.getTelNumber());

        if (oauthSignupReq.getCompanyId() != null && oauthSignupReq.isCeo()){
            CompanyEntity company = companyRepository.findById(oauthSignupReq.getCompanyId())
                    .orElseThrow(CompanyNotFound::new);
            user.updateCompany(company);
            user.authorizeAdmin();
            user.editStatus(UserStatus.APPROVED);
        }
        else if (oauthSignupReq.isBroker()){
            user.authorizeEmployee();
            user.editStatus(UserStatus.PENDING);
        }
        else {
            user.authorizeCustomer();
            user.editStatus(UserStatus.APPROVED);
        }

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Boolean validateEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void approveEmployee(String ceoEmail, String employeeEmail){
        UserEntity ceo = userRepository.findWithCompanyByEmail(ceoEmail)
                .orElseThrow(UserNotFound::new);

        if(ceo.getRole() != Role.ADMIN || ceo.getCompany() == null){
            throw new BrokerPermissionException();
        }

        UserEntity employee = userRepository.findByEmail(employeeEmail)
                .orElseThrow(UserNotFound::new);
        employee.editStatus(UserStatus.APPROVED);
        employee.updateCompany(ceo.getCompany());

        userRepository.save(employee);
    }
}
