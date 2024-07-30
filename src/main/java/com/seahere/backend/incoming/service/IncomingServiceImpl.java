package com.seahere.backend.incoming.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.incoming.repository.IncomingRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IncomingServiceImpl implements IncomingService{

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final IncomingRepository incomingRepository;

    @Override
    public void save(Long companyId, Long userId, IncomingDataRequest incomingDataRequest) {

        CompanyEntity companyEntity = companyRepository.findById(companyId).orElseThrow(CompanyNotFound::new);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        IncomingEntity incomingEntity = incomingDataRequest.toEntity();
        incomingEntity.enroll(userEntity,companyEntity);
        incomingRepository.save(incomingEntity);
    }
}
