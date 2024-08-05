package com.seahere.backend.incoming.service;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;

public interface IncomingService {
    void save(Long companyId, Long userId, IncomingDataRequest incomingDataRequest);
}