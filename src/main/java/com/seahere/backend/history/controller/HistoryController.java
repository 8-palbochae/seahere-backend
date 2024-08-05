package com.seahere.backend.history.controller;

import com.seahere.backend.history.controller.request.HistoryGetReq;
import com.seahere.backend.history.controller.response.HistoryAdjustResponse;
import com.seahere.backend.history.controller.response.HistoryOutgoingResponse;
import com.seahere.backend.history.controller.response.HistoryResponse;
import com.seahere.backend.history.service.HistoryService;
import com.seahere.backend.history.controller.response.HistoryIncomingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/histories")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/adjusts/{date}")
    public ResponseEntity<List<HistoryAdjustResponse>> adjustsList(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<HistoryAdjustResponse> result = historyService.findByAdjustList(1L, date).stream().map(HistoryAdjustResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }
    @GetMapping("/outgoings/{date}")
    public ResponseEntity<List<HistoryOutgoingResponse>> outgoingList(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("search") String search) {
        log.info("date = {} search = {}",date,search);
        List<HistoryOutgoingResponse> result = historyService.findByOutgoingList(1L, date,search).stream().map(HistoryOutgoingResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/incomings/{date}")
    public ResponseEntity<List<HistoryIncomingResponse>> incomingList(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("날짜는 = {}",date);
        List<HistoryIncomingResponse> result = historyService.findByIncomingList(1L, date)
                .stream()
                .map(HistoryIncomingResponse::from)
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
