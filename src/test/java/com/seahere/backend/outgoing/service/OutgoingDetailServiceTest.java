package com.seahere.backend.outgoing.service;

import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingDetailState;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql(value = "/sql/outgoing-detail-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OutgoingDetailServiceTest {

    @Autowired
    private OutgoingDetailService outgoingDetailService;

    @Autowired
    private OutgoingDetailJpaRepository outgoingDetailJpaRepository;

    @Autowired
    private EntityManager em;
    @Test
    @DisplayName("일치하는 출고 상세 번호에 대항하는 출고 상세의 상태를 delete로 바꾼다.")
    void stateToDelete() {
        //given
        outgoingDetailService.deleteOutgoingDetail(1L);
        //when
        OutgoingDetailEntity result = outgoingDetailJpaRepository.findById(1L).get();
        //then
        assertThat(result.getState()).isEqualTo(OutgoingDetailState.DELETE);
    }
    @Test
    @DisplayName("일치하는 출고 상세 번호에 대항하는 출고 상세의 상태를 state로 바꾼다.")
    void stateToState() {
        //given
        OutgoingDetailEntity detail = outgoingDetailJpaRepository.findById(1L).get();
        //when
        detail.stateToActive();
        em.flush();
        em.clear();
        OutgoingDetailEntity result = outgoingDetailJpaRepository.findById(1L).get();
        //then
        assertThat(result.getState()).isEqualTo(OutgoingDetailState.ACTIVE);
    }
}