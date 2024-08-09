package com.seahere.backend.sales.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.sales.controller.request.IncomingWeekRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class IncomingSalesController {

    @PostMapping("/incoming/week")
    public ResponseEntity findIncomingWeek(@RequestBody IncomingWeekRequest incomingWeekRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
