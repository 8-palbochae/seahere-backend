package com.seahere.backend.incoming.service;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.dto.IncomingReqDto;

import java.time.LocalDate;
import java.util.List;

public interface IncomingService {
    void save(Long companyId, Long userId, IncomingDataRequest incomingDataRequest);
    List<IncomingReqDto> findIncomingList(Long companyId, LocalDate startDate, LocalDate endDate);
}