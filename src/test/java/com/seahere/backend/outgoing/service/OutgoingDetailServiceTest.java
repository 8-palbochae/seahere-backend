package com.seahere.backend.outgoing.service;

import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@Transactional
@Sql(value = "/sql/outgoing-detail-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OutgoingDetailServiceTest {

    @Autowired
    private OutgoingDetailService outgoingDetailService;
    @Autowired
    private OutgoingJpaRepository outgoingJpaRepository;
    @Autowired
    private CompanyRepository companyRepository;


    @Test
    @DisplayName("")
    void test() {
    }
}