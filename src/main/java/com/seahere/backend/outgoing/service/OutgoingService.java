package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OutgoingService {
    private final OutgoingJpaRepository outgoingJpaRepository;
    private final OutgoingRepository outgoingRepository;

    public List<OutgoingEntity> findByOutgoingStateIsPending(Long companyId){
        return outgoingRepository.findByOutgoingStateIsPending(companyId);
    }

    public void save(OutgoingEntity outgoingEntity){
        outgoingJpaRepository.save(outgoingEntity);
    }

}
