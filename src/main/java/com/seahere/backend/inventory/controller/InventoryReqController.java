package com.seahere.backend.inventory.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.inventory.controller.request.CustomerInventorySearch;
import com.seahere.backend.inventory.controller.request.InventoryReqDetailSearchRequest;
import com.seahere.backend.inventory.controller.request.InventoryReqSearchRequest;
import com.seahere.backend.inventory.controller.response.*;
import com.seahere.backend.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryReqController {
    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<InventoryReqListResponse> inventoryReqList(InventoryReqSearchRequest searchRequest, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize(), Sort.by(Sort.Direction.ASC, "name"));
        Slice<InventoryResponse> results = inventoryService.findPagedInventoryByCompanyId(customUserDetails.getUser().getCompanyId(), pageRequest, searchRequest.getSearch());
        InventoryReqListResponse inventoryReqListResponse = new InventoryReqListResponse(results);
        return ResponseEntity.ok(inventoryReqListResponse);
    }

    @GetMapping("/details")
    public ResponseEntity<InventoryReqDetailListResponse> inventoryReqDetailList(InventoryReqDetailSearchRequest detailSearchRequest, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        PageRequest pageRequest = PageRequest.of(detailSearchRequest.getPage(), detailSearchRequest.getSize(), Sort.by(Sort.Direction.ASC, "incomingDate"));
        Slice<InventoryDetailResponse> results = inventoryService.findPagedProductsByCompanyId(customUserDetails.getUser().getCompanyId(), detailSearchRequest.getName(), detailSearchRequest.getCategory(), pageRequest);
        InventoryReqDetailListResponse inventoryReqDetailListResponse = new InventoryReqDetailListResponse(results);
        return ResponseEntity.ok(inventoryReqDetailListResponse);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<List<CustomerInventoryRes>> customerInventoryListGet(@PathVariable Long companyId, 
                                                                               @RequestBody CustomerInventorySearch customerInventorySearch){
        List<CustomerInventoryRes> brokerInventoryList = inventoryService.getBrokerInventoryList(companyId, customerInventorySearch);
        return ResponseEntity.ok(brokerInventoryList);
    }
}
