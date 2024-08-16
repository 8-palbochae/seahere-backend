package com.seahere.backend.alarm.controller;

import com.seahere.backend.alarm.controller.request.DiscountInventories;
import com.seahere.backend.alarm.controller.request.DiscountRequest;
import com.seahere.backend.alarm.controller.request.TokenRequest;
import com.seahere.backend.alarm.controller.response.InventoryRes;
import com.seahere.backend.alarm.service.AlarmService;
import com.seahere.backend.alarm.service.DiscountService;
import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;
    private final InventoryService inventoryService;
    private final DiscountService discountService;

    @PostMapping("/firebase-token")
    public void tokenSave(@RequestBody TokenRequest tokenRequest, @AuthenticationPrincipal CustomUserDetails userDetails){
        log.info("TokenRequest = {}, userId = {}", tokenRequest.getToken(),userDetails.getUser().getUserId());
        alarmService.saveToken(userDetails.getUser().getUserId(),tokenRequest.getToken());
    }

    @GetMapping("/alarm/inventories")
    public ResponseEntity<List<InventoryRes>> customerInventoryListGet(@AuthenticationPrincipal CustomUserDetails userDetails){
        List<InventoryRes> results = inventoryService.getBrokerInventoryList(userDetails.getUser().getCompanyId());
        return ResponseEntity.ok(results);
    }

    @PostMapping("/alarm/discounts")
    public void alarmDiscount(@RequestBody DiscountRequest request, @AuthenticationPrincipal CustomUserDetails userDetails){
        log.info("request = {}", request);
        discountService.discountInventory(userDetails.getUser().getCompanyId(),request);
    }
}
