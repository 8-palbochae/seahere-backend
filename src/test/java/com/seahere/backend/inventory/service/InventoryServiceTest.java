package com.seahere.backend.inventory.service;

import com.seahere.backend.inventory.controller.response.InventoryReqDetailDto;
import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@Sql(value = "/sql/inventory-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Slf4j
@SpringBootTest
class InventoryServiceTest {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryJpaRepository inventoryJpaRepository;

    @Test
    @DisplayName("companyId를 통한 재고 목록 조회")
    void test1() throws Exception {
        // when
        Long companyId = 101L;
        String search = "";
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Page<InventoryReqDto> inventoryReqDtoSlice = inventoryJpaRepository.findPagedInventoryByCompanyId(companyId, search, pageRequest);
        Page<InventoryReqDetailDto> inventoryReqDetailDtoPage1 = inventoryJpaRepository.findPagedProductsByCompanyId(companyId, "광어", "활어", pageRequest);
        inventoryReqDtoSlice.forEach(inventory -> log.info("Inventory: {}", inventory.toString()));
        inventoryReqDetailDtoPage1.forEach(product -> log.info("Product before remove: {}", product.toString()));
        inventoryJpaRepository.deleteById(1L);

        // then
        Page<InventoryReqDetailDto> inventoryReqDetailDtoPage2 = inventoryJpaRepository.findPagedProductsByCompanyId(companyId, "광어", "활어", pageRequest);

        inventoryReqDetailDtoPage2.forEach(product -> log.info("Product after remove: {}", product.toString()));
    }

    @Test
    @DisplayName("어종, 상태, 자연, 나라,회사번호가 같은 재고가있다면 true를 반환한다.")
    void checkInventory(){
        //given
        //when
        boolean result = inventoryJpaRepository.findByCategoryAndNameAndCompanyIdAndNaturalStatusAndCountry(
                "활어", "광어", 101L, "자연", "국산"
        ).isPresent();
        //then
        assertThat(result).isTrue();

    }
}