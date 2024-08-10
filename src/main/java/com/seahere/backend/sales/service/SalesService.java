package com.seahere.backend.sales.service;

import com.seahere.backend.sales.dto.IncomingSalesDto;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {

    List<IncomingSalesDto> findIncomingWeek(LocalDate startDate, LocalDate endDate, Long companyId);
}
