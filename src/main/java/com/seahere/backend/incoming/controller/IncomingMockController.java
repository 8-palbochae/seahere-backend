package com.seahere.backend.incoming.controller;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.incoming.dto.IncomingMockDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class IncomingMockController {

    @PostMapping("/saveIncomingData")
    public ResponseEntity<String> saveIncomingData(@RequestBody IncomingDataRequest incomingDataRequest) {
        log.info("받은데이터 ={}", incomingDataRequest.toString());
        return ResponseEntity.ok("데이터를 성공적으로 받았습니다.");
    }
}
