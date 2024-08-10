package com.seahere.backend.sales.service;

import com.seahere.backend.sales.dto.IncomingSalesDto;
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
    public List<IncomingSalesDto> findIncomingWeek(LocalDate startDate, LocalDate endDate, Long companyId) {
        return salesRepository.incomingWeekList(companyId, startDate, endDate);
    }
}
