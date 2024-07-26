package com.seahere.backend.user.service;

import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.SignupReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public Long signupUser(SignupReq signupReq) {
        UserEntity userEntity = UserEntity.from(signupReq);
        userRepository.save(userEntity);
        return userEntity.getId();
    }
}
