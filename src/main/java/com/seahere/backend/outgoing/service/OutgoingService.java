package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OutgoingService {
    private final OutgoingJpaRepository outgoingJpaRepository;
    private final OutgoingRepository outgoingRepository;

    public Slice<OutgoingEntity> findByOutgoingStateIsPending(Long companyId, Pageable pageable){
        return outgoingRepository.findByOutgoingStateIsPending(companyId,pageable);
    }

    public void save(OutgoingEntity outgoingEntity){
        outgoingJpaRepository.save(outgoingEntity);
    }

}
