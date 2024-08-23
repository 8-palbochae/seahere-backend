package com.seahere.backend.incoming.service;

import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(value = "/sql/incoming-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class IncomingServiceImplTest {

    @Autowired
    private IncomingService incomingService;
    @Autowired
    private InventoryJpaRepository inventoryJpaRepository;
    @Test
    @DisplayName("입고할때 동시성 확인")
    void save() throws InterruptedException {
        // given
        IncomingDataRequest request = IncomingDataRequest.builder().incomingPrice(1000)
                .productId(1L)
                .natural("자연")
                .category("양식")
                .country("국산")
                .quantity(1)
                .build();
        int threadCount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        // when
        incomingService.save(1L,1L,request);
        for(int i = 0; i < threadCount; i++){
            executorService.submit(() ->{
                try{
                    incomingService.save(1L,1L,request);
                }catch (RuntimeException e){
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        InventoryEntity inventory = inventoryJpaRepository.findByCategoryAndProductNameAndCompanyIdAndNaturalStatusAndCountry("양식", "광어", 1L, "자연", "국산").get();
        // then
        assertThat(inventory.getQuantity()).isEqualTo(31);
    }


}