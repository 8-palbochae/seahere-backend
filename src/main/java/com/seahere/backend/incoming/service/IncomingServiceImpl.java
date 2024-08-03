package com.seahere.backend.incoming.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.dto.IncomingCountDto;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.incoming.repository.IncomingJpaRepository;
import com.seahere.backend.incoming.repository.IncomingRepository;
import com.seahere.backend.inventory.service.InventoryService;
import com.seahere.backend.product.entity.ProductEntity;
import com.seahere.backend.product.repository.ProductRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class IncomingServiceImpl implements IncomingService{

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final IncomingRepository incomingRepository;
    private final IncomingJpaRepository incomingJpaRepository;
    private final InventoryService inventoryService;

    @Override
    public void save(Long companyId, Long userId, IncomingDataRequest incomingDataRequest) {
        ProductEntity productEntity = productRepository.findById(incomingDataRequest.getProductId()).orElse(null);
        CompanyEntity companyEntity = companyRepository.findById(companyId).orElseThrow(CompanyNotFound::new);
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        IncomingEntity incomingEntity = incomingDataRequest.toEntity(productEntity);
        incomingEntity.enroll(userEntity,companyEntity);
        inventoryService.inventoryUpdateEnroll(companyId, incomingDataRequest);
        incomingJpaRepository.save(incomingEntity);
    }

    public List<IncomingCountDto> findIncomingCountList(Long companyId, LocalDate startDate, LocalDate endDate){
        return incomingRepository.findIncomingCountList(companyId, startDate, endDate);
    }

    @Override
    public List<IncomingEntity> findIncomingList(Long companyId, LocalDate incomingDate) {
        return incomingRepository.findIncomingList(companyId, incomingDate);
    }
}