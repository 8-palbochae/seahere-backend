package com.seahere.backend.history.service;

import com.seahere.backend.history.dto.HistoryListDto;
import com.seahere.backend.history.repository.HistoryRepository;
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

    public List<HistoryListDto> findByHistoryList(LocalDate startDate, LocalDate endDate){
        return historyRepository.findByHistoryDate(startDate,endDate);
    }
}
