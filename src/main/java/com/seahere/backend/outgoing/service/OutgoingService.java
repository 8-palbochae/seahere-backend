package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.exception.OutgoingNotFoundException;
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

    public Slice<OutgoingEntity> findByOutgoingStateIsPending(Long companyId, Pageable pageable, LocalDate startDate, LocalDate endDate, String search){
        return outgoingRepository.findByOutgoingStateIsPending(companyId,pageable, startDate, endDate, search);
    }
    @Transactional
    public void save(OutgoingEntity outgoingEntity){
        outgoingJpaRepository.save(outgoingEntity);
    }
    @Transactional
    public OutgoingEntity changeOutgoingState(Long outgoingId, OutgoingState state){
        OutgoingEntity outgoingReq = outgoingJpaRepository.findById(outgoingId).orElseThrow(OutgoingNotFoundException::new);
        outgoingReq.changeState(state);
        return outgoingReq;
    }
}
