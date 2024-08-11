package com.seahere.backend.sales.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.sales.controller.request.IncomingWeekRequest;
import com.seahere.backend.sales.controller.response.IncomingWeekRes;
import com.seahere.backend.sales.dto.IncomingSalesDto;
import com.seahere.backend.sales.service.SalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @PostMapping("/incoming/week")
    public ResponseEntity<List<IncomingWeekRes>> findIncomingWeek(@RequestBody IncomingWeekRequest incomingWeekRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<IncomingSalesDto> result = salesService.findIncomingWeek(
                incomingWeekRequest.getStartDate(),
                incomingWeekRequest.getEndDate(),
                userDetails.getUser().getCompanyId()
        );

        List<IncomingWeekRes> responseList = result.stream()
                .map(dto -> IncomingWeekRes.builder()
                        .incomingDate(dto.getIncomingDate())
                        .week(dto.getWeek())
                        .incomingPrice(dto.getIncomingPrice())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
