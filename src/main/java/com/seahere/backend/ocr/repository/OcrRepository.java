package com.seahere.backend.ocr.repository;

import com.seahere.backend.ocr.controller.request.MultipartFileRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@Slf4j
public class OcrRepository {

    // RestTemplate을 직접 인스턴스화
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String CLOVA_OCR_API_URL = "https://7n6f2iipra.apigw.ntruss.com/custom/v1/33393/ea7766b1bdfaafa82caf417d026f4c7c270112e21f9f70860d596f42f8dcc17c/document/biz-license";
    private static final String CLOVA_OCR_SECRET_KEY = "RUducW9Id1BWU1hjTVpOU0lYQXpZY3NlVHFxc1BuSXk=";  // 실제 시크릿 키로 변경

    public String callOcrApi(byte[] imageBytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "multipart/form-data");
        headers.set("X-OCR-SECRET", CLOVA_OCR_SECRET_KEY);

        // JSON 메시지 구성
        String jsonMessage = "{\n" +
                "    \"version\": \"V2\",\n" +
                "    \"requestId\": \"uuid\",\n" +
                "    \"timestamp\": " + System.currentTimeMillis() + ",\n" +
                "    \"images\": [\n" +
                "        {\n" +
                "            \"format\": \"jpg\",\n" +
                "            \"name\": \"test\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // 멀티파트 요청 구성
        MultipartFileRequestBody multipartBody = new MultipartFileRequestBody(imageBytes, jsonMessage);
        HttpEntity<MultipartFileRequestBody> requestEntity = new HttpEntity<>(multipartBody, headers);

        // 클로바 OCR API 호출
        ResponseEntity<String> response = restTemplate.exchange(
                CLOVA_OCR_API_URL, HttpMethod.POST, requestEntity, String.class);

        return response.getBody();
    }
}
