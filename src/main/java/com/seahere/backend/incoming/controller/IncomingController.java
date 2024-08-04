package com.seahere.backend.incoming.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.controller.request.IncomingDateRequest;
import com.seahere.backend.incoming.controller.request.IncomingPeriodRequest;
import com.seahere.backend.incoming.controller.response.IncomingHistoryResponse;
import com.seahere.backend.incoming.controller.response.IncomingResponse;
import com.seahere.backend.incoming.service.IncomingService;
import com.seahere.backend.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    //incomingDate, count 반환
    @GetMapping("/incomings")
    public ResponseEntity<List<IncomingHistoryResponse>> incomingCountIncomingDate(IncomingPeriodRequest periodRequest) {
        List<IncomingHistoryResponse> result = incomingService.findIncomingCountList(1L,
                        periodRequest.getStartDate(), periodRequest.getEndDate()).stream()
                .map(IncomingHistoryResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


    @GetMapping("/incomings/detail")
    public ResponseEntity<List<IncomingResponse>> incomingReqList(IncomingDateRequest dateRequest) {
        List<IncomingResponse> result = incomingService.findIncomingList(1L, dateRequest.getIncomingDate())
                .stream()
                .map(IncomingResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

//    @GetMapping("/info")
//    public String test(@AuthenticationPrincipal CustomUserDetails userDetails){
//        UserEntity user = userDetails.getUser();
//        /**
//         * 유저 엔티티를 서비스 메서드의 파라미터로 전달
//         * 서비스에서 user.getCompany();
//         */
//    }
}