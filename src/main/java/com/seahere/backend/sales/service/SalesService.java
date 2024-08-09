package com.seahere.backend.sales.service;

import java.time.LocalDate;
import java.util.List;

public interface SalesService {

    List findIncomingWeek(LocalDate startDate, LocalDate endDate, Long companyId);
}
