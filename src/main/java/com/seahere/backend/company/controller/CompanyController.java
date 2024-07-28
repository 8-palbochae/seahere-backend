package com.seahere.backend.company.controller;

import com.seahere.backend.company.request.CompanyCreateReq;
import com.seahere.backend.company.response.CompanyResponse;
import com.seahere.backend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<Long> companyAdd(@RequestBody CompanyCreateReq companyCreateReq){
        Long savedId = companyService.save(companyCreateReq);
        return ResponseEntity.ok(savedId);
    }
}
