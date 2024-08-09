package com.seahere.backend.ocr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seahere.backend.ocr.controller.response.ExtractOcrResponse;
import com.seahere.backend.ocr.repository.OcrRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class OcrService {

    private final OcrRepository ocrRepository;

    public OcrService(OcrRepository ocrRepository) {
        this.ocrRepository = ocrRepository;
    }

    public ExtractOcrResponse processOcr(MultipartFile file) {
        try {
            // 파일을 바이트 배열로 변환
            byte[] imageBytes = file.getBytes();

            // OCR API 호출
            String ocrResponseJson = ocrRepository.callOcrApi(imageBytes);

            // 응답 데이터 로깅
            log.info("OCR Response JSON: {}", ocrResponseJson);

            // JSON 데이터를 ExtractOcrResponse 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            // 예상치 못한 필드를 무시하도록 설정
            objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ExtractOcrResponse ocrResponse = objectMapper.readValue(ocrResponseJson, ExtractOcrResponse.class);

            return ocrResponse;

        } catch (IOException e) {
            log.error("Error processing OCR", e);
            throw new RuntimeException("Failed to process OCR", e);
        }
    }
}
