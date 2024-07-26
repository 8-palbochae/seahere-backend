package com.seahere.backend.outgoing.controller;

import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDetailsDto;
import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDto;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class OutgoingReqController {

    @GetMapping("/outgoings")
    public ResponseEntity<List> outgoingReqList(@RequestParam(value = "search" ,defaultValue = "") String search,
                                                @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        log.info("search = {}, startDate = {}, endDate = {}",search,startDate,endDate);
        List<OutgoingReqMockDto> mockList = new ArrayList<>();
        mockList.add(new OutgoingReqMockDto(1L,"스파로스","광어외3...","요청"));
        mockList.add(new OutgoingReqMockDto(2L,"kdt","우럭외3...","요청"));
        mockList.add(new OutgoingReqMockDto(3L,"부산시","고등어외3...","요청"));
        mockList.add(new OutgoingReqMockDto(4L,"신세계","갈치외3...","요청"));
        mockList.add(new OutgoingReqMockDto(5L,"아이앤씨","다시마외3...","요청"));
        mockList.add(new OutgoingReqMockDto(6L,"아이앤씨","다시마외3...","요청"));
        mockList.add(new OutgoingReqMockDto(7L,"아이앤씨","다시마외3...","요청"));
        mockList.add(new OutgoingReqMockDto(8L,"아이앤씨","다시마외3...","요청"));
        mockList = mockList.stream().filter(item -> item.getCustomerName().contains(search)).collect(Collectors.toList());
        return ResponseEntity.ok(mockList);
    }
    @GetMapping("/outgoings/{outgoingId}")
    public ResponseEntity<List> outgoingReqDetailList(@PathVariable("outgoingId") Long outgoingId){
        List<OutgoingReqMockDetailsDto> mockDetailList = new ArrayList<>();
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 1L,"", "광어",20,100,80,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 2L,"", "넙치",30,100,70,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 3L,"", "고등어",40,100,60,200000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 4L,"", "갈치",50,100,50,300000));
        return ResponseEntity.ok(mockDetailList);
    }

}
