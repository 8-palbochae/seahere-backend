package com.seahere.backend.incoming.controller;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.controller.request.IncomingPeriodRequest;
import com.seahere.backend.incoming.controller.response.IncomingResponse;
import com.seahere.backend.incoming.entity.IncomingEntity;
import com.seahere.backend.incoming.service.IncomingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IncomingController {

    private final IncomingService incomingService;

    private static final long companyId = 4L;
    private static final long userId = 5L;

    @PostMapping("/incoming")
    public ResponseEntity<String> saveIncomingData(@RequestBody IncomingDataRequest incomingDataRequest) {
        incomingService.save(companyId, userId, incomingDataRequest);

        return ResponseEntity.ok("데이터를 성공적으로 받았습니다.");

    }

    @GetMapping("/incomings")
    public ResponseEntity<List<IncomingResponse>> incomingReqList(IncomingPeriodRequest periodRequest) {

        List<IncomingEntity> results = incomingService.findIncomingList(1L,
                 periodRequest.getStartDate(), periodRequest.getEndDate());


        List<IncomingResponse> incomingResponseList = results.stream()
                .map(IncomingResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(incomingResponseList);
    }
}

