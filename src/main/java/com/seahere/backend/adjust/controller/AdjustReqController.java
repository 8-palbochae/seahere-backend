package com.seahere.backend.adjust.controller;

import com.seahere.backend.adjust.controller.request.AdjustRequest;
import com.seahere.backend.adjust.service.AdjustService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/adjust")
@RequiredArgsConstructor
public class AdjustReqController {
    private final AdjustService adjustService;

    @PostMapping()
    public ResponseEntity<String> saveAdjustReq(@RequestBody AdjustRequest adjustRequest){
        adjustService.save(adjustRequest);
        return ResponseEntity.ok("조정 성공");
    }
}