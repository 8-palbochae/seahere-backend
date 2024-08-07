package com.seahere.backend.inventory.service;

import com.seahere.backend.common.entity.ProductCountry;
import com.seahere.backend.common.entity.ProductSource;
import com.seahere.backend.common.entity.ProductStatus;
import com.seahere.backend.incoming.controller.request.IncomingDataRequest;
import com.seahere.backend.inventory.controller.response.InventoryResponse;
import com.seahere.backend.inventory.entity.InventoryDetailEntity;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import com.seahere.backend.inventory.repository.InventoryRepository;
import com.seahere.backend.product.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "/sql/inventory-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Slf4j
@SpringBootTest
class InventoryServiceTest {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryJpaRepository inventoryJpaRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    @DisplayName("companyId를 통한 재고 목록 조회")
    void test1() throws Exception {
        // when
        Long companyId = 101L;
        String search = "";
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Slice<InventoryResponse> inventoryReqDtoSlice = inventoryRepository.findPagedInventoryByCompanyId(companyId, search, pageRequest);

        // then
        inventoryReqDtoSlice.forEach(inventory -> log.info("Inventory: {}", inventory.toString()));
    }

    @Test
    @DisplayName("어종, 상태, 자연, 나라,회사번호가 같은 재고가있다면 true를 반환한다.")
    void checkInventory(){
        //given
        // when
        boolean result = inventoryJpaRepository.existsByCategoryAndProductNameAndCompanyIdAndNaturalStatusAndCountry(
                "활어", "광어", 101L, "자연", "국산"
        );

        // then
        assertThat(result).isTrue();

    }

    @Transactional
    @Test
    @DisplayName("입고가 처음 진행되고 재고가 생성되면 재고 상세 정보도 함께 등록된다.")
    void test2() throws Exception {
        //given
        IncomingDataRequest request = IncomingDataRequest.builder()
                .productId(7L)
                .quantity(100)
                .incomingPrice(1000000)
                .memo("테스트")
                .countryDetail("테스트")
                .natural(ProductSource.NATURAL.toString())
                .category(ProductStatus.DEAD.toString())
                .country(ProductCountry.ABOARD.toString())
                .build();

        //when
        InventoryEntity result = inventoryService.inventoryUpdateEnroll(101L, request);
        InventoryDetailEntity inventoryDetail = result.getInventoryDetail();

        //then
        Assertions.assertNotNull(inventoryDetail);
    }

    @Test
    @DisplayName("companyId를 통한 보유 상품 목록 조회")
    void test3() throws Exception {
        // when
        Long companyId = 101L;
        List<ProductDto> inventoryReqList = inventoryRepository.findAllDistinctProductNamesByCompanyId(companyId);
        // then
        inventoryReqList.forEach(productDto -> log.info("Inventory: {}", productDto.toString()));
    }
}