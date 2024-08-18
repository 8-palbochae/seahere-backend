package com.seahere.backend.company.controller;

import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.company.controller.request.CompanyCreateReq;
import com.seahere.backend.company.controller.request.CompanySearch;
import com.seahere.backend.company.controller.response.CompanyResponse;
import com.seahere.backend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>> getList(@ModelAttribute CompanySearch companySearch) {
        List<CompanyResponse> companyResponses = companyService.getList(companySearch);
        return ResponseEntity.ok(companyResponses);
    }

    @GetMapping("/trade/companies")
    public ResponseEntity<List<CompanyResponse>> getTradeList(@ModelAttribute CompanySearch companySearch,  @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CompanyResponse> companyResponses = companyService.getTradeCompany(companySearch,userDetails.getUser().getCompanyId());
        return ResponseEntity.ok(companyResponses);
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<CompanyResponse> getCompany(@PathVariable Long companyId){
        CompanyResponse companyResponse = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(companyResponse);
    }

    @GetMapping("/companies/best")
    public ResponseEntity<CompanyResponse> getBestCompany(){
        CompanyResponse mostOutgoingCompany = companyService.getMostOutgoingCompany();
        return ResponseEntity.ok(mostOutgoingCompany);
    }
}
