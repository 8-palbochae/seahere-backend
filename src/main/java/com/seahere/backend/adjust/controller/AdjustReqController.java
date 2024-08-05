package com.seahere.backend.adjust.controller;

import com.seahere.backend.adjust.controller.request.AdjustListRequest;
import com.seahere.backend.adjust.controller.request.AdjustRequest;
import com.seahere.backend.adjust.entity.AdjustEntity;
import com.seahere.backend.adjust.service.AdjustService;
import com.seahere.backend.inventory.controller.request.InventoryReqSearchRequest;
import com.seahere.backend.inventory.controller.response.InventoryReqListResponse;
import com.seahere.backend.inventory.controller.response.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/adjust")
@RequiredArgsConstructor
public class AdjustReqController {
    private final AdjustService adjustService;

    @PostMapping()
    public ResponseEntity<String> saveAdjustReq(@RequestBody AdjustRequest adjustRequest){
        try {
            log.info("Received adjust request: {}", adjustRequest);

            if (adjustRequest.getInventoryId() == null) {
                log.error("Inventory ID must not be null");
                return ResponseEntity.badRequest().body("Inventory ID must not be null");
            }

            adjustService.save(adjustRequest);
            return ResponseEntity.ok("조정 성공");
        } catch (IllegalArgumentException e) {
            log.error("Error processing adjust request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error processing adjust request: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
