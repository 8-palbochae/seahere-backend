package com.seahere.backend.sales.repository;

import com.seahere.backend.sales.dto.IncomingMonthDto;
import com.seahere.backend.sales.dto.IncomingWeekDto;

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository {

    List<IncomingWeekDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate);
    List<IncomingMonthDto> incomingMonthList(Long companyId, LocalDate startDate, LocalDate endDate);
}
