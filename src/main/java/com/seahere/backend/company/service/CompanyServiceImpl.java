package com.seahere.backend.company.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.company.request.CompanyCreateReq;
import com.seahere.backend.company.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    @Override
    public CompanyResponse getCompanyById(Long id) {
        CompanyEntity company = companyRepository.findById(id).orElseThrow(RuntimeException::new);

        return CompanyResponse.from(company);
    }

    @Override
    public CompanyResponse getCompanyByRegNumber(String registrationNumber) {
        CompanyEntity company = companyRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(RuntimeException::new);

        return CompanyResponse.from(company);
    }

    @Override
    public List<CompanyResponse> getCompanyByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName)
                .stream()
                .map(CompanyResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long save(CompanyCreateReq companyCreateReq) {
        CompanyEntity company = CompanyEntity.from(companyCreateReq);
        companyRepository.save(company);

        return company.getId();
    }

    @Transactional
    @Override
    public CompanyResponse editProfileImage(Long id, String profileImage) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        company.editProfileImage(profileImage);
        return CompanyResponse.from(company);
    }
}
