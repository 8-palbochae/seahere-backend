package com.seahere.backend.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.seahere.backend.common.exception.SeaHereException;
import com.seahere.backend.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import com.seahere.backend.inventory.service.InventoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {
    InventoryService inventoryService;

    @GetMapping("/inventories")
    public ResponseEntity<List<String>> getMockData() {
        List<String> mock = new ArrayList<>();
        mock.add("광어");
        mock.add("광수");
        mock.add("이광수");
        mock.add("광명");
        mock.add("광자");
        mock.add("광광");
        mock.add("새우");
        mock.add("우럭");
        mock.add("밀치");
        return ResponseEntity.ok(mock);
    }
}
