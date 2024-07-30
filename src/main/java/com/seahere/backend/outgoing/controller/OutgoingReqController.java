package com.seahere.backend.outgoing.controller;

import com.seahere.backend.outgoing.controller.request.OutgoingReqSearchRequest;
import com.seahere.backend.outgoing.controller.request.OutgoingStateChangeRequest;
import com.seahere.backend.outgoing.controller.response.OutgoingReqListResponse;
import com.seahere.backend.outgoing.controller.response.OutgoingReqMockDetailsDto;
import com.seahere.backend.outgoing.dto.OutgoingReqDto;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
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
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/outgoings")
@RequiredArgsConstructor
public class OutgoingReqController {

    private final OutgoingService outgoingService;

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
        return null;
    }

    @PatchMapping("/{outgoingId}")
    public ResponseEntity<OutgoingReqDto> outgoingStateChange(@PathVariable("outgoingId") Long outgoingId, @RequestBody OutgoingStateChangeRequest request){
        OutgoingState state = OutgoingState.from(request.getState());
        OutgoingReqDto outgoing = outgoingService.changeOutgoingState(outgoingId,state);
        return ResponseEntity.ok(outgoing);

    }
    @DeleteMapping("/details/{outgoingDetailId}")
    public void outgoingDetailDelete(@PathVariable("outgoingDetailId")Long detailId){
    }
}
