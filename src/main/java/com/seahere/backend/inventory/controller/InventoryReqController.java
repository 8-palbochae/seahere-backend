package com.seahere.backend.inventory.controller;

import com.seahere.backend.inventory.controller.request.InventoryReqDetailSearchRequest;
import com.seahere.backend.inventory.controller.request.InventoryReqSearchRequest;
import com.seahere.backend.inventory.controller.response.InventoryDetailResponse;
import com.seahere.backend.inventory.controller.response.InventoryReqDetailListResponse;
import com.seahere.backend.inventory.controller.response.InventoryResponse;
import com.seahere.backend.inventory.controller.response.InventoryReqListResponse;
import com.seahere.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryReqController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<InventoryReqListResponse> inventoryReqList(InventoryReqSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), Sort.by(Sort.Direction.ASC, "name"));
        Slice<InventoryResponse> results = inventoryService.findPagedInventoryByCompanyId(searchRequest.getCompanyId(), pageRequest, searchRequest.getSearch());
        InventoryReqListResponse inventoryReqListResponse = new InventoryReqListResponse(results);
        return ResponseEntity.ok(inventoryReqListResponse);
    }

    @GetMapping("/details")
    public ResponseEntity<InventoryReqDetailListResponse> inventoryReqDetailList(InventoryReqDetailSearchRequest detailSearchRequest) {
        PageRequest pageRequest = PageRequest.of(detailSearchRequest.getPage(), detailSearchRequest.getSize(), Sort.by(Sort.Direction.ASC, "incomingDate"));
        Slice<InventoryDetailResponse> results = inventoryService.findPagedProductsByCompanyId(detailSearchRequest.getCompanyId(), detailSearchRequest.getName(), detailSearchRequest.getCategory(), pageRequest);
        InventoryReqDetailListResponse inventoryReqDetailListResponse = new InventoryReqDetailListResponse(results);
        return ResponseEntity.ok(inventoryReqDetailListResponse);
    }
}
