package com.seahere.backend.history.service;

import com.seahere.backend.history.controller.request.HistoryGetReq;
import com.seahere.backend.history.dto.HistoryListDto;
import com.seahere.backend.history.repository.HistoryRepository;
import com.seahere.backend.incoming.repository.IncomingRepository;
import com.seahere.backend.outgoing.repository.OutgoingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<HistoryListDto> findByHistoryList(LocalDate startDate, LocalDate endDate){
        return historyRepository.findByHistoryDate(startDate,endDate);
    }
}
