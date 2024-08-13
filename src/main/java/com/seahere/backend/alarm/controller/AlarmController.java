package com.seahere.backend.alarm.controller;

import com.seahere.backend.alarm.controller.request.TokenRequest;
import com.seahere.backend.alarm.service.AlarmService;
import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.inventory.controller.response.CustomerInventoryRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping("/firebase-token")
    public void tokenSave(@RequestBody TokenRequest tokenRequest, @AuthenticationPrincipal CustomUserDetails userDetails){
        log.info("TokenRequest = {}, userId = {}", tokenRequest.getToken(),userDetails.getUser().getUserId());
        alarmService.saveToken(userDetails.getUser().getUserId(),tokenRequest.getToken());
    }

    @GetMapping("/alarm/inventories")
    public ResponseEntity<List<CustomerInventoryRes>> customerInventoryListGet(@PathVariable Long companyId,
}
