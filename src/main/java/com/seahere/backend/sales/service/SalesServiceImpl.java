package com.seahere.backend.sales.service;

import com.seahere.backend.sales.dto.IncomingMonthDto;
import com.seahere.backend.sales.dto.IncomingWeekDto;
import com.seahere.backend.sales.repository.SalesRepository;
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
public class SalesServiceImpl implements SalesService {

    private final SalesRepository salesRepository;

    @Override
    public List<IncomingWeekDto> findIncomingWeek(LocalDate startDate, LocalDate endDate, Long companyId) {
        return salesRepository.incomingWeekList(companyId, startDate, endDate);
    }

    @Override
    public List<IncomingMonthDto> findIncomingMonth(LocalDate startDate, LocalDate endDate, Long companyId) {
        return salesRepository.incomingMonthList(companyId, startDate, endDate);
    }
}
