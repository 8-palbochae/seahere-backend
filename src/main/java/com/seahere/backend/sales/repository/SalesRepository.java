package com.seahere.backend.sales.repository;

import com.seahere.backend.sales.dto.IncomingSalesDto;

import java.time.LocalDate;
import java.util.List;

public interface SalesRepository {

    List<IncomingSalesDto> incomingWeekList(Long companyId, LocalDate startDate, LocalDate endDate);
}
