package com.seahere.backend.outgoing.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.outgoing.dto.OutgoingReqDto;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.exception.OutgoingReqNotFoundException;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OutgoingService {
    private final OutgoingJpaRepository outgoingJpaRepository;
    private final OutgoingRepository outgoingRepository;
    private final CompanyRepository companyRepository;

    public Slice<OutgoingEntity> findByOutgoingStateIsPending(Long companyId, Pageable pageable, LocalDate startDate, LocalDate endDate, String search){
        CompanyEntity company = companyRepository.findById(companyId).orElseThrow(CompanyNotFound::new);
        return outgoingRepository.findByOutgoingStateIsPending(company,pageable, startDate, endDate, search);
    }
    @Transactional
    public void save(OutgoingEntity outgoingEntity){
        outgoingJpaRepository.save(outgoingEntity);
    }
    @Transactional
    public OutgoingEntity changeOutgoingState(Long outgoingId, OutgoingState state){
        OutgoingEntity outgoingReq = outgoingJpaRepository.findById(outgoingId).orElseThrow(OutgoingReqNotFoundException::new);
        outgoingReq.changeState(state);
        return outgoingReq;
    }
}
