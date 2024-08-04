package com.seahere.backend.inventory.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.inventory.controller.request.InventoryEditReq;
import com.seahere.backend.inventory.controller.response.InventoryResponse;
import com.seahere.backend.inventory.controller.response.InventorySettingRes;
import com.seahere.backend.inventory.service.InventoryDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InventoryDetailController {
    private final InventoryDetailService inventoryDetailService;

    @PostMapping("inventories/detail/{inventoryId}")
    public ResponseEntity<InventorySettingRes> inventoryDetailPost(@PathVariable Long inventoryId, @RequestBody InventoryEditReq inventoryEditReq){
        InventorySettingRes inventorySettingRes = inventoryDetailService.editInventoryDetail(inventoryId, inventoryEditReq);
        return ResponseEntity.ok(inventorySettingRes);
    }
}
