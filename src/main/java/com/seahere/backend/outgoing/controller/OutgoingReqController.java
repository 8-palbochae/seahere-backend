package com.seahere.backend.outgoing.controller;

import com.seahere.backend.outgoing.controller.request.OutgoingReqSearchRequest;
import com.seahere.backend.outgoing.controller.request.OutgoingStateChangeRequest;
import com.seahere.backend.outgoing.controller.response.OutgoingReqListResponse;
import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDetailsDto;
import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDto;
import com.seahere.backend.outgoing.dto.OutgoingReqDto;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.exception.InvalidOutgoingStateException;
import com.seahere.backend.outgoing.service.OutgoingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/outgoings")
@RequiredArgsConstructor
public class OutgoingReqController {
    private final static String STATE = "state";

    private final OutgoingService outgoingService;
    List<OutgoingReqMockDetailsDto> mockDetailList;
    @PostConstruct
    void init() {
        mockDetailList = new ArrayList<>();
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 1L,"", "광어",20,100,80,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 2L,"", "넙치",30,100,70,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 3L,"", "고등어",40,100,60,200000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(1L, 4L,"", "갈치",50,100,50,300000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(2L, 5L,"", "광어",20,100,80,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(2L, 6L,"", "넙치",30,100,70,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(2L, 7L,"", "고등어",40,100,60,200000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(2L, 8L,"", "갈치",50,100,50,300000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(3L, 9L,"", "광어",20,100,80,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(3L, 10L,"", "넙치",30,100,70,100000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(3L, 11L,"", "고등어",40,100,60,200000));
        mockDetailList.add(new OutgoingReqMockDetailsDto(3L, 12L,"", "갈치",50,100,50,300000));
    }

    @GetMapping()
    public ResponseEntity<OutgoingReqListResponse> outgoingReqList(OutgoingReqSearchRequest request){
        log.info("size = {} page = {}",request.getSize(),request.getPage());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize(), Sort.by(Sort.Direction.DESC, "outgoingId"));
        Slice<OutgoingEntity> results = outgoingService.findByOutgoingStateIsPending(1L, pageRequest
                , request.getStartDate(), request.getEndDate(), request.getSearch());
        OutgoingReqListResponse outgoingReqListResponse = new OutgoingReqListResponse(results);
        return ResponseEntity.ok(outgoingReqListResponse);
    }

    @GetMapping("/{outgoingId}")
    public ResponseEntity<List<OutgoingReqMockDetailsDto>> outgoingReqDetailList(@PathVariable("outgoingId") Long outgoingId){
        ArrayList<OutgoingReqMockDetailsDto> list = mockDetailList.stream().filter(item -> item.getOutgoingId().equals(outgoingId)).collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/{outgoingId}")
    public ResponseEntity<OutgoingReqDto> outgoingStateChange(@PathVariable("outgoingId") Long outgoingId, @RequestBody OutgoingStateChangeRequest request){
        OutgoingState state = OutgoingState.from(request.getState());
        OutgoingReqDto outgoing = outgoingService.changeOutgoingState(outgoingId,state);
        return ResponseEntity.ok(outgoing);

    }
    @DeleteMapping("/details/{outgoingDetailId}")
    public void outgoingDetailDelete(@PathVariable("outgoingDetailId")Long detailId){
        int index = (int) (detailId -1);
        mockDetailList.remove(index);
    }
}
