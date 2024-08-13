package com.seahere.backend.sales.repository;

import com.seahere.backend.sales.dto.SalesMonthDto;
import com.seahere.backend.sales.dto.SalesWeekDto;

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository {

    List<SalesWeekDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate);
    List<SalesMonthDto> incomingMonthList(Long companyId, LocalDate startDate, LocalDate endDate);
    List<SalesWeekDto> outgoingWeekList(Long companyId, LocalDate startDate, LocalDate endDate);
    List<SalesMonthDto> outgoingMonthList(Long companyId, LocalDate startDate, LocalDate endDate);
}
