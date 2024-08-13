package com.seahere.backend.follow.service;


import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.follow.controller.response.FollowReqResponse;
import com.seahere.backend.follow.entity.FollowEntity;
import com.seahere.backend.follow.repository.FollowJpaRepository;
import com.seahere.backend.follow.repository.FollowRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowJpaRepository followJpaRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public List<FollowReqResponse> findFollowingByCustomerId(Long userId, String search) {
        return followRepository.findAllFollowingByUserId(userId);
    }

    public void save(Long userId, Long companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId).orElseThrow(CompanyNotFound::new);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        FollowEntity followEntity = FollowEntity.builder()
                .user(userEntity)
                .company(companyEntity)
                .build();
        followJpaRepository.save(followEntity);
    }

    public void delete(Long userId, Long followId){
        followJpaRepository.deleteByFollowIdAndUserId(userId, followId);
    }
}
