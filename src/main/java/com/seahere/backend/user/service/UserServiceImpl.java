package com.seahere.backend.user.service;

import com.seahere.backend.common.entity.Role;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;
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
        UserEntity user = customerSignupReq.to();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signupBroker(BrokerSignupReq brokerSignupReq) {
        UserEntity user = brokerSignupReq.to();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long signupCeo(CeoSignupReq ceoSignupReq) {
        UserEntity user = ceoSignupReq.to();
        user.passwordEncode(passwordEncoder);

        CompanyEntity company = companyRepository.findById(ceoSignupReq.getCompanyId())
                .orElseThrow(CompanyNotFound::new);
        user.updateCompany(company);

        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Boolean validateEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
