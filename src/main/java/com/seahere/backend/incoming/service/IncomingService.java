package com.seahere.backend.incoming.service;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.controller.request.IncomingEditReq;
import com.seahere.backend.incoming.dto.IncomingCountDto;
import com.seahere.backend.incoming.entity.IncomingEntity;

import java.time.LocalDate;
import java.util.List;

public interface IncomingService {
    void save(Long companyId, Long userId, IncomingDataRequest incomingDataRequest);
    List<IncomingCountDto> findIncomingCountList(Long companyId, LocalDate startDate, LocalDate endDate);
    List<IncomingEntity> findIncomingList(Long companyId, LocalDate incomingDate);
    Long editIncoming(IncomingEditReq incomingEditReq);
}