package com.seahere.backend.sales.service;

import com.seahere.backend.sales.dto.IncomingMonthDto;
import com.seahere.backend.sales.dto.IncomingWeekDto;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {

    List<IncomingWeekDto> findIncomingWeek(LocalDate startDate, LocalDate endDate, Long companyId);
    List<IncomingMonthDto> findIncomingMonth(LocalDate startDate, LocalDate endDate, Long companyId);
}
