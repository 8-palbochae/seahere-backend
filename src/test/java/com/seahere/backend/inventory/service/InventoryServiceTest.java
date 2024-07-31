package com.seahere.backend.inventory.service;

import com.seahere.backend.inventory.controller.response.InventoryReqDto;
import com.seahere.backend.inventory.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/sql/inventory-service-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Slf4j
@SpringBootTest
class InventoryServiceTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    @DisplayName("companyId를 통한 재고 목록 조회")
    void test1() throws Exception {
        // when
        Long companyId = 101L;
        String search = "";
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name"));
        Slice<InventoryReqDto> inventoryReqDtoSlice = inventoryRepository.findPagedInventoryByCompanyId(companyId, search, pageRequest);

        // then
        inventoryReqDtoSlice.forEach(inventory -> log.info("Inventory: {}", inventory.toString()));
    }
}
