package com.seahere.backend.company.service;

import com.seahere.backend.company.controller.request.CompanyCreateReq;
import com.seahere.backend.company.controller.request.CompanySearch;
import com.seahere.backend.company.controller.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse getCompanyById(Long id);
    CompanyResponse getCompanyByRegNumber(String registrationNumber);
    List<CompanyResponse> getCompanyByCompanyName(String companyName);
    Long save(CompanyCreateReq companyCreateReq);
    CompanyResponse editProfileImage(Long id, String profileImage);

    List<CompanyResponse> getList(CompanySearch companySearch);

    CompanyResponse getMostOutgoingCompany();
    List<CompanyResponse> getTradeCompany(CompanySearch companySearch, Long companyId);
}
