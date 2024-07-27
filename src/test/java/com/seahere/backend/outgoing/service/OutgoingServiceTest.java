package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@Transactional
class OutgoingServiceTest {
    @Autowired
    private OutgoingService outgoingService;
    @Autowired
    private OutgoingDetailJpaRepository outgoingDetailJpaRepository;
    @PersistenceUnit
    EntityManagerFactory emf;
    @BeforeEach
    void init(){
        OutgoingEntity data1 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.now())
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingDetailEntity ddata1 = OutgoingDetailEntity.builder().price(BigDecimal.ZERO).outgoing(data1)
                .quantity(3).build();
        OutgoingEntity data2 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.now())
                .partialOutgoing(true)
                .outgoingState(OutgoingState.ready)
                .companyId(1L)
                .build();
        OutgoingEntity data3 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.now())
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingEntity data4 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.now())
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingEntity data5 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.now())
                .partialOutgoing(true)
                .outgoingState(OutgoingState.ready)
                .companyId(1L)
                .build();
        outgoingService.save(data1);
        outgoingDetailJpaRepository.save(ddata1);
        outgoingService.save(data2);
        outgoingService.save(data3);
        outgoingService.save(data4);
        outgoingService.save(data5);
    }

    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending)인 출고 목록을 반환한다.")
    void findByOutgoingStateIsPending() {
        //when
        List<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L);
        boolean fetch = emf.getPersistenceUnitUtil().isLoaded(result.get(0).getOutgoingDetails());
        //then
        assertThat(fetch).isTrue();
        assertThat(result).hasSize(3)
                .extracting("companyId","outgoingState","partialOutgoing")
                .contains(
                        tuple(1L,OutgoingState.pending,true),
                        tuple(1L,OutgoingState.pending,true),
                        tuple(1L,OutgoingState.pending,true)
                );
    }

    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending)이 일치하는게 없다면 빈 리스트를 반환한다.")
    void findByOutgoingStateIsPendingIsEmpty() {
        //when
        List<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(2L);
        //then
        assertThat(result).isEmpty();
    }
}