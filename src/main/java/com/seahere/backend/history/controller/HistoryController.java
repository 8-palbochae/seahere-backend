package com.seahere.backend.history.controller;

import com.seahere.backend.history.controller.request.HistoryGetReq;
import com.seahere.backend.history.controller.response.HistoryResponse;
import com.seahere.backend.history.dto.HistoryListDto;
import com.seahere.backend.history.service.HistoryService;
import com.seahere.backend.incoming.controller.request.IncomingDateRequest;
import com.seahere.backend.incoming.controller.response.IncomingResponse;
import com.seahere.backend.incoming.service.IncomingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/histories")
public class HistoryController {
    private IncomingService incomingService;
    private HistoryService historyService;

    @GetMapping("/incomings/detail")
    public ResponseEntity<List<IncomingResponse>> incomingReqList(IncomingDateRequest dateRequest) {
        List<IncomingResponse> result = incomingService.findIncomingList(1L, dateRequest.getIncomingDate())
                .stream()
                .map(IncomingResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<List<HistoryResponse>> historyList(HistoryGetReq historyGetReq){
        log.info("historyGetReq  startDate= {}, endDate = {}",historyGetReq.getStartDate(),historyGetReq.getEndDate());
        List<HistoryResponse> result = historyService.findByHistoryList(historyGetReq.getStartDate(), historyGetReq.getEndDate()).stream().map(HistoryResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
