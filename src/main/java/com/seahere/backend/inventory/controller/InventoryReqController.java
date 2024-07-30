package com.seahere.backend.inventory.controller;

import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.controller.response.InventoryReqListResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.seahere.backend.inventory.service.InventoryService;

@RestController
@Slf4j
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryReqController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<InventoryReqListResponse> inventoryReqList(@RequestParam Long companyId, @RequestParam int size, @RequestParam int page, @RequestParam String search) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "name"));
        Page<InventoryReqDto> results = inventoryService.findPagedInventoryByCompanyId(companyId, pageRequest, search);
        InventoryReqListResponse inventoryReqListResponse = new InventoryReqListResponse(results);
        return ResponseEntity.ok(inventoryReqListResponse);
    }
}
