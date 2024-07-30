package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingDetailState;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.exception.OutgoingDetailNotFoundException;
import com.seahere.backend.outgoing.exception.OutgoingNotFoundException;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OutgoingDetailService {

    private final OutgoingDetailJpaRepository outgoingDetailJpaRepository;
    private final OutgoingJpaRepository outgoingJpaRepository;

    @Transactional
    public void deleteOutgoingDetail(Long outgoingDetailId){
        OutgoingDetailEntity detail = outgoingDetailJpaRepository.findById(outgoingDetailId).orElseThrow(OutgoingDetailNotFoundException::new);
        detail.stateToDelete();
    }

    public List<OutgoingDetailEntity> findByOutgoingAndStateIsAcitve(Long outgoingId){
        OutgoingEntity outgoing = outgoingJpaRepository.findById(outgoingId).orElseThrow(OutgoingNotFoundException::new);
        return outgoingDetailJpaRepository.findByOutgoingAndState(outgoing, OutgoingDetailState.ACTIVE);
    }

    @Transactional
    public void updateByOutgoingDetailStateToActive(Long outgoingId){
        OutgoingEntity outgoing = outgoingJpaRepository.findById(outgoingId).orElseThrow(OutgoingNotFoundException::new);
        outgoingDetailJpaRepository.updateByOutgoingDetailStateToActive(outgoing,OutgoingDetailState.ACTIVE);
    }
}
