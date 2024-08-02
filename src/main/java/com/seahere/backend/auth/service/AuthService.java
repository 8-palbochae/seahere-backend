package com.seahere.backend.auth.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.response.CompanyResponse;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public CompanyResponse getSocialUserWithCompany(String email){
        Optional<UserEntity> optionalUser = userRepository.findWithCompanyByEmail(email);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if(user.getCompany() != null){
                CompanyEntity company = user.getCompany();
                return CompanyResponse.from(company);
            }
        }
        return null;
    }
}
