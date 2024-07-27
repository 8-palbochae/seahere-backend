package com.seahere.backend.outgoing.controller;

import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDetailsDto;
import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDto;
import com.seahere.backend.outgoing.entity.OutgoingState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class OutgoingReqController {
    List<OutgoingReqMockDto> mockList;
    List<OutgoingReqMockDetailsDto> mockDetailList;
    @PostConstruct
    void init() {
        log.info("Initializing mock data...");
        mockList = new ArrayList<>();
        mockDetailList = new ArrayList<>();
        mockList.add(new OutgoingReqMockDto(1L,"스파로스","광어외3...", OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(2L,"kdt","우럭외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(3L,"부산시","고등어외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(4L,"신세계","갈치외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(5L,"아이앤씨","다시마외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(6L,"아이앤씨","다시마외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(7L,"아이앤씨","다시마외3...",OutgoingState.pending));
        mockList.add(new OutgoingReqMockDto(8L,"아이앤씨","다시마외3...",OutgoingState.pending));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 1L,"", "광어",20,100,80,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 2L,"", "넙치",30,100,70,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 3L,"", "고등어",40,100,60,200000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 4L,"", "갈치",50,100,50,300000));
    }

    @GetMapping("/outgoings")
    public ResponseEntity<List> outgoingReqList(@RequestParam(value = "search" ,defaultValue = "") String search,
                                                @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        ArrayList<OutgoingReqMockDto> list = mockList.stream().filter(item -> item.getCustomerName().contains(search))
                .filter(item -> item.getState().equals(OutgoingState.pending))
                .collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.ok(list);
    }
    @GetMapping("/outgoings/{outgoingId}")
    public ResponseEntity<List> outgoingReqDetailList(@PathVariable("outgoingId") Long outgoingId){
        return ResponseEntity.ok(mockDetailList);
    }

    @PatchMapping("/outgoings/{outgoingId}")
    public ResponseEntity<OutgoingReqMockDto> outgoingStateChange(@PathVariable("outgoingId") Long outgoingId, @RequestBody Map<String,OutgoingState> state){
        log.info("outgoingId = {} state = {}",outgoingId,state.get("state"));
        int index = (int) (outgoingId -1);
        OutgoingReqMockDto item = mockList.get(index);
        if(isStateToReady(state.get("state"))){
            item.setOutgoingStateToReady();
        }
        if(isStateToReject(state.get("state"))){
            item.setOutgoingStateToReject();
        }
        log.info("item = {}",item);
        return ResponseEntity.ok(item);
    }

    private boolean isStateToReady(OutgoingState state){
        return state == OutgoingState.ready;
    }

    private boolean isStateToReject(OutgoingState state){
        return state == OutgoingState.reject;
    }
}
