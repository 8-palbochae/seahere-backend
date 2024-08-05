package com.seahere.backend.history.service;

import com.seahere.backend.adjust.repository.AdjustRepository;
import com.seahere.backend.history.dto.HistoryListDto;
import com.seahere.backend.history.repository.HistoryRepository;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.incoming.repository.IncomingRepository;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final IncomingRepository incomingRepository;
    private final AdjustRepository ajAdjustRepository;
    private final OutgoingRepository outgoingRepository;

    public List<HistoryListDto> findByHistoryList(Long companyId,LocalDate startDate, LocalDate endDate){
        return historyRepository.findByHistoryDate(companyId,startDate,endDate);
    }

    public List<IncomingEntity> findByIncomingList(Long companyId, LocalDate incomingDate) {
        return incomingRepository.findIncomingList(companyId, incomingDate);
    }

    public List<OutgoingEntity> findByOutgoingList(Long companyId, LocalDate outgoingDate,String search){
        return outgoingRepository.findByOutgoingStateIsNotPendingAndDate(companyId,outgoingDate,search);
    }

}