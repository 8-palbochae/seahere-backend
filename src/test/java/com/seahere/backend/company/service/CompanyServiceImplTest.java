package com.seahere.backend.company.service;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.company.request.CompanyCreateReq;
import com.seahere.backend.company.response.CompanyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceImplTest {
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    void clear(){
        companyRepository.deleteAll();
    }

    @Test
    @DisplayName("CompanyCreateReq를 통해서 회사 등록이 가능하다.")
    void test1() throws Exception {
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("부산광역시")
                .subAddress("스파로스 아카데미")
                .build();

        CompanyCreateReq companyCreateReq = CompanyCreateReq.builder()
                .companyName("여보소수산")
                .registrationNumber("123456")
                .address(address)
                .build();
        //when
        Long savedId = companyService.save(companyCreateReq);
        CompanyEntity result = companyRepository.findById(savedId).orElseThrow(CompanyNotFound::new);

        //then
        assertEquals(savedId,result.getId());
        assertEquals("여보소수산",result.getCompanyName());
        assertEquals("123456",result.getRegistrationNumber());
    }

    @Transactional
    @Test
    @DisplayName("Company ID로 회사 정보 조회가 가능하다")
    void test2() throws Exception {
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("부산광역시")
                .subAddress("스파로스 아카데미")
                .build();

        CompanyEntity company = CompanyEntity.builder()
                .companyName("여보소수산")
                .registrationNumber("123456")
                .address(address)
                .build();

        companyRepository.save(company);
        //when

        CompanyResponse result = companyService.getCompanyById(company.getId());

        //then
        assertEquals(result.getCompanyName(),"여보소수산");
        assertEquals(result.getRegistrationNumber(),"123456");
    }

    @Test
    @DisplayName("사업자 번호로 회사 정보 조회가 가능하다")
    void test3() throws Exception {
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("부산광역시")
                .subAddress("스파로스 아카데미")
                .build();

        CompanyEntity company = CompanyEntity.builder()
                .companyName("여보소수산")
                .registrationNumber("123456")
                .address(address)
                .build();

        companyRepository.save(company);
        //when

        CompanyResponse result = companyService.getCompanyByRegNumber("123456");

        //then
        assertEquals(result.getCompanyName(),"여보소수산");
        assertEquals(result.getRegistrationNumber(),"123456");
    }
}
