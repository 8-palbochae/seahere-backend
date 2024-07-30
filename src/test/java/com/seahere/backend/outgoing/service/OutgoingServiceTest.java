package com.seahere.backend.outgoing.service;

import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.exception.CompanyNotFound;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import com.seahere.backend.outgoing.repository.OutgoingJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
@Sql(value = "/sql/outgoing-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class OutgoingServiceTest {
    //todo 추후에 user도 entity로 바꿀것
    @Autowired
    private OutgoingService outgoingService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private OutgoingJpaRepository outgoingJpaRepository;


    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호가 없다면 예외를 던진다.")
    void findByOutgoingStateIsPendingIsEmpty() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        //then
        assertThatThrownBy(() ->
                outgoingService.findByOutgoingStateIsPending(2L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),""))
                .isInstanceOf(CompanyNotFound.class)
                .hasMessage("존재하는 회사가 없습니다.");
    }

    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이의 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSlice(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        CompanyEntity company = companyRepository.findById(1L).get();
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),"");

        //then
        assertThat(result.getContent()).hasSize(3)
                .extracting("company","outgoingState","partialOutgoing")
                .contains(
                        tuple(company,OutgoingState.PENDING,true),
                        tuple(company,OutgoingState.PENDING,true),
                        tuple(company,OutgoingState.PENDING,true)
                );
    }
    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이 그리고 이름이 아리가 포함된 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSliceSearch(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        CompanyEntity company = companyRepository.findById(1L).get();
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,8,20),"아리");

        //then
        assertThat(result.getContent()).hasSize(1)
                .extracting("company","outgoingState","partialOutgoing","customerName")
                .contains(
                        tuple(company,OutgoingState.PENDING,true,"아리랑")
                );
    }
    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이 그리고 상품이름이 광어가 포함된 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSliceSearchProductName(){
        //given
        CompanyEntity company = companyRepository.findById(1L).get();
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),"광어");
        //then
        assertThat(result.getContent().get(0).getOutgoingDetails().get(0).getProductName()).isEqualTo("광어");
        assertThat(result.getContent()).hasSize(2)
                .extracting("company","outgoingState","partialOutgoing","customerName")
                .contains(
                        tuple(company,OutgoingState.PENDING,true,"아리랑"),
                        tuple(company,OutgoingState.PENDING,true,"스리랑")
                );
    }

    @Test
    @DisplayName("출고대기(ready)로 출고의 상태를 변경한다")
    void changeOutgoingStateToReady() {
        // given
        OutgoingEntity outgoing = outgoingJpaRepository.findById(1L).get();
        // when
        outgoing.changeState(OutgoingState.READY);
        OutgoingEntity result = outgoingJpaRepository.findById(1L).get();
        // then
        assertThat(result.getOutgoingState()).isEqualTo(OutgoingState.READY);
    }

    @Test
    @DisplayName("출고완료(complete)로 출고의 상태를 변경한다")
    void changeOutgoingStateToComplete() {
        // given
        OutgoingEntity outgoing = outgoingJpaRepository.findById(1L).get();
        // when
        outgoing.changeState(OutgoingState.COMPLETE);
        OutgoingEntity result = outgoingJpaRepository.findById(1L).get();
        // then
        assertThat(result.getOutgoingState()).isEqualTo(OutgoingState.COMPLETE);
    }
}