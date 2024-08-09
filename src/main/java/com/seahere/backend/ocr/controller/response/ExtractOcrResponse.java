package com.seahere.backend.ocr.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 이 어노테이션을 추가
public class ExtractOcrResponse {
    private String companyName;
    private String representativeName;
    private String businessNumber;
    private String address;
    private String issueDate;
}
