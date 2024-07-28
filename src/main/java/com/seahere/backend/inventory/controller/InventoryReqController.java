package com.seahere.backend.inventory.controller;

import com.seahere.backend.inventory.controller.request.InventoryReqSearchRequest;
import com.seahere.backend.inventory.controller.response.InventoryRespMockDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryRespMockDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.seahere.backend.inventory.service.InventoryService;

import java.util.*;

@RestController
@Slf4j
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryReqController {
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryReqListResponse>> inventoryReqList(InventoryReqSearchRequest inventoryReqSearchRequest){
        List<InventoryRespMockDto> resultList = inventoryService.findAllInventoryBySearch();
        return  ResponseEntity.ok(resultList);

    }
}