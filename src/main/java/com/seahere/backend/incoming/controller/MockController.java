package com.seahere.backend.incoming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MockController {

    @GetMapping("/product-search-incoming")
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
