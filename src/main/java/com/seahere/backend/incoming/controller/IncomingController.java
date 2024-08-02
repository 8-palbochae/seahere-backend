package com.seahere.backend.incoming.controller;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.controller.request.IncomingPeriodRequest;
import com.seahere.backend.incoming.controller.response.IncomingPeriodResponse;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.incoming.service.IncomingService;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IncomingController {

    private final IncomingService incomingService;

    private static final long companyId = 4L;
    private static final long userId = 5L;

    @PostMapping("/saveIncomingData")
    public ResponseEntity<String> saveIncomingData(@RequestBody IncomingDataRequest incomingDataRequest) {
        incomingService.save(companyId, userId, incomingDataRequest);

        return ResponseEntity.ok("데이터를 성공적으로 받았습니다.");

    }

    @GetMapping("/incomings")
    public ResponseEntity<String> incomingReqList(IncomingPeriodRequest periodRequest) {
        LocalDate startDate =periodRequest.getStartDate();
        LocalDate endDate =periodRequest.getEndDate();
        log.info("startDate: {}, endDate: {}", startDate, endDate);
        List<IncomingEntity> results = incomingService.findIncomingList(1L,
                 periodRequest.getStartDate(), periodRequest.getEndDate());
        for(IncomingEntity incomingEntity : results){
            log.info("natural = {}",incomingEntity.getNaturalStatus());
        }
        return ResponseEntity.ok("데이터를 성공적으로 받았습니다.");
    }

}

