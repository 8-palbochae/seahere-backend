package com.seahere.backend.user.service;

import com.seahere.backend.common.entity.Role;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signupCustomer(CustomerSignupReq customerSignupReq) {
        UserEntity user = customerSignupReq.to();
        user.passwordEncode(passwordEncoder);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Boolean validateEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
