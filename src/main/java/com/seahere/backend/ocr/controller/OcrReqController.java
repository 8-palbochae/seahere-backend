package com.seahere.backend.ocr.controller;

import com.seahere.backend.ocr.controller.response.ExtractOcrResponse;
import com.seahere.backend.ocr.service.OcrService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/ocr")
@RequiredArgsConstructor
public class OcrReqController {

    private final OcrService ocrService;

    @PostMapping
    public ResponseEntity<ExtractOcrResponse> handleOcrRequest(@RequestPart("file") MultipartFile file) {
        log.info("Received OCR request for file: {}", file.getOriginalFilename());

        ExtractOcrResponse response = ocrService.processOcr(file);

        return ResponseEntity.ok(response);
    }
}
