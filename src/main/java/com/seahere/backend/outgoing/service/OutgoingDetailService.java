package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.dto.OutgoingDetailDto;
import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.exception.OutgoingDetailNotFoundException;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OutgoingDetailService {

    private final OutgoingDetailJpaRepository outgoingDetailJpaRepository;

    @Transactional
    public void deleteOutgoingDetail(Long outgoingDetailId){
        OutgoingDetailEntity detail = outgoingDetailJpaRepository.findById(outgoingDetailId).orElseThrow(OutgoingDetailNotFoundException::new);
        detail.stateToDelete();
    }

    public List<OutgoingDetailDto>

}
