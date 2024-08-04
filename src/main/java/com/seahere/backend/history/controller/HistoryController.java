package com.seahere.backend.history.controller;

import com.seahere.backend.history.controller.request.HistoryGetReq;
import com.seahere.backend.history.controller.response.HistoryResponse;
import com.seahere.backend.history.service.HistoryService;
import com.seahere.backend.incoming.controller.response.IncomingResponse;
import com.seahere.backend.incoming.service.IncomingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/histories")
public class HistoryController {
    private final IncomingService incomingService;
    private final HistoryService historyService;

    @GetMapping("/incomings/{date}")
    public ResponseEntity<List<IncomingResponse>> incomingReqList(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("날짜는 = {}",date);
        List<IncomingResponse> result = incomingService.findIncomingList(1L, date)
                .stream()
                .map(IncomingResponse::from)
                .collect(Collectors.toList());
        log.info("size = {}",result.size());
        return ResponseEntity.ok(result);
    }

    @GetMapping()
    public ResponseEntity<List<HistoryResponse>> historyList(HistoryGetReq historyGetReq){
        log.info("historyGetReq  startDate= {}, endDate = {}",historyGetReq.getStartDate(),historyGetReq.getEndDate());
        List<HistoryResponse> result = historyService.findByHistoryList(1L,historyGetReq.getStartDate(), historyGetReq.getEndDate()).stream().map(HistoryResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
}
