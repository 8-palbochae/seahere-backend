package com.seahere.backend.outgoing.service;

import com.seahere.backend.outgoing.entity.OutgoingDetailEntity;
import com.seahere.backend.outgoing.entity.OutgoingEntity;
import com.seahere.backend.outgoing.entity.OutgoingState;
import com.seahere.backend.outgoing.repository.OutgoingDetailJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest
@Transactional
@Slf4j
class OutgoingServiceTest {
    @Autowired
    private OutgoingService outgoingService;
    @Autowired
    private OutgoingDetailJpaRepository outgoingDetailJpaRepository;
    @Autowired
    private EntityManager em;

    @PersistenceUnit
    EntityManagerFactory emf;
    @BeforeEach
    void init(){
        OutgoingEntity data1 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.of(2024,7,20))
                .customerName("아리랑")
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingDetailEntity ddata1 = OutgoingDetailEntity.builder().productName("광어").price(BigDecimal.ZERO)
                .quantity(3).build();
        data1.addOutgoingDetail(ddata1);
        OutgoingEntity data2 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.of(2024,7,28))
                .customerName("스리랑")
                .partialOutgoing(true)
                .outgoingState(OutgoingState.ready)
                .companyId(1L)
                .build();
        OutgoingEntity data3 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.of(2024,7,28))
                .customerName("스리랑")
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingDetailEntity ddata2 = OutgoingDetailEntity.builder().productName("광어").price(BigDecimal.ZERO)
                .quantity(3).build();
        data3.addOutgoingDetail(ddata2);
        OutgoingEntity data4 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.of(2024,7,28))
                .customerName("스리랑")
                .partialOutgoing(true)
                .outgoingState(OutgoingState.pending)
                .companyId(1L)
                .build();
        OutgoingEntity data5 = OutgoingEntity.builder()
                .outgoingDate(LocalDate.of(2024,7,28))
                .customerName("스리랑")
                .partialOutgoing(true)
                .outgoingState(OutgoingState.ready)
                .companyId(1L)
                .build();
        outgoingService.save(data1);
        outgoingService.save(data2);
        outgoingService.save(data3);
        outgoingService.save(data4);
        outgoingService.save(data5);
        outgoingDetailJpaRepository.save(ddata1);
        outgoingDetailJpaRepository.save(ddata2);
        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending)이 일치하는게 없다면 빈 리스트를 반환한다.")
    void findByOutgoingStateIsPendingIsEmpty() {
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(2L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),"");
        //then
        assertThat(result.getContent()).isEmpty();
    }

    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이의 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSlice(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),"");
        boolean fetch = emf.getPersistenceUnitUtil().isLoaded(result.getContent().get(0).getOutgoingDetails());

        //then
        assertThat(result.getContent()).hasSize(3)
                .extracting("companyId","outgoingState","partialOutgoing")
                .contains(
                        tuple(1L,OutgoingState.pending,true),
                        tuple(1L,OutgoingState.pending,true),
                        tuple(1L,OutgoingState.pending,true)
                );
    }
    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이 그리고 이름이 아리가 포함된 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSliceSearch(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,8,20),"아리");
        boolean fetch = emf.getPersistenceUnitUtil().isLoaded(result.getContent().get(0).getOutgoingDetails());

        //then
        assertThat(result.getContent()).hasSize(1)
                .extracting("companyId","outgoingState","partialOutgoing","customerName")
                .contains(
                        tuple(1L,OutgoingState.pending,true,"아리랑")
                );
    }
    @Test
    @DisplayName("출고 리스트중 상태가 해당 회사 번호와 출고요청(pending), 지정날짜 사이 그리고 상품이름이 광어가 포함된 출고 목록을 반환한다.")
    void findByOutgoingStateIsPendingSliceSearchProductName(){
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "outgoingId"));
        //when
        Slice<OutgoingEntity> result = outgoingService.findByOutgoingStateIsPending(1L, pageRequest,LocalDate.of(2024,7,20),LocalDate.of(2024,7,30),"광어");
        //then
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getContent().get(0).getOutgoingDetails().get(0).getProductName()).isEqualTo("광어");
        log.info("size = {}",result.getContent().get(0).getOutgoingDetails().size());
        assertThat(result.getContent()).hasSize(2)
                .extracting("companyId","outgoingState","partialOutgoing","customerName")
                .contains(
                        tuple(1L,OutgoingState.pending,true,"아리랑"),
                        tuple(1L,OutgoingState.pending,true,"스리랑")
                );
    }

}