package com.seahere.backend.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seahere.backend.inventory.controller.request.InventoryReqSearchRequest;
import com.seahere.backend.inventory.entity.InventoryEntity;
import com.seahere.backend.inventory.repository.InventoryJpaRepository;
import com.seahere.backend.inventory.service.InventoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InventoryControllerTest {
    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryJpaRepository inventoryJpaRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("InventoryReqSearchRequest 클래스로 GET 요청시 CompanyId에 따른 재고 목록이 반환된다.")
    public void test1() throws Exception{
        //given

        InventoryReqSearchRequest inventoryReqSearchRequest = InventoryReqSearchRequest.builder()
                .companyId(101L)
                .size(10)
                .page(0)
                .build();

        LocalDate date1 = LocalDate.of(2024, 7, 23);
        LocalDate date2 = LocalDate.of(2024, 7, 21);
        LocalDate date3 = LocalDate.of(2024,7,22);
        LocalDate date4 = LocalDate.of(2024,7,12);
        LocalDate date5 = LocalDate.of(2024,7,9);
        LocalDate date6 = LocalDate.of(2024,7,31);
        LocalDate date7 = LocalDate.of(2024,7,14);
        //given
        InventoryEntity inventory01 = InventoryEntity.builder()
                .inventoryId(1L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date7)
                .build();

        InventoryEntity inventory02 = InventoryEntity.builder()
                .inventoryId(2L)
                .name("우럭")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("양식")
                .companyId(101L)
                .incomingDate(date1)
                .build();

        InventoryEntity inventory03 = InventoryEntity.builder()
                .inventoryId(3L)
                .name("광어")
                .category("선어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date4)
                .build();
        InventoryEntity inventory04 = InventoryEntity.builder()
                .inventoryId(4L)
                .name("우럭")
                .category("활어")
                .quantity(30)
                .country("일본")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date3)
                .build();

        InventoryEntity inventory05 = InventoryEntity.builder()
                .inventoryId(5L)
                .name("광어")
                .category("선어")
                .quantity(16)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date5)
                .build();

        InventoryEntity inventory06 = InventoryEntity.builder()
                .inventoryId(6L)
                .name("광어")
                .category("선어")
                .quantity(10)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date7)
                .build();
        InventoryEntity inventory07 = InventoryEntity.builder()
                .inventoryId(7L)
                .name("우럭")
                .category("활어")
                .quantity(17)
                .country("국산")
                .naturalStatus("양식")
                .companyId(101L)
                .incomingDate(date6)
                .build();

        InventoryEntity inventory08 = InventoryEntity.builder()
                .inventoryId(8L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date2)
                .build();

        InventoryEntity inventory09 = InventoryEntity.builder()
                .inventoryId(9L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date4)
                .build();

        InventoryEntity inventory10 = InventoryEntity.builder()
                .inventoryId(10L)
                .name("숭어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("양식")
                .companyId(101L)
                .incomingDate(date7)
                .build();

        InventoryEntity inventory11 = InventoryEntity.builder()
                .inventoryId(11L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date7)
                .build();

        InventoryEntity inventory12 = InventoryEntity.builder()
                .inventoryId(12L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date5)
                .build();

        InventoryEntity inventory13 = InventoryEntity.builder()
                .inventoryId(13L)
                .name("민어")
                .category("활어")
                .quantity(10)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date6)
                .build();

        InventoryEntity inventory14 = InventoryEntity.builder()
                .inventoryId(14L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date7)
                .build();

        InventoryEntity inventory15 = InventoryEntity.builder()
                .inventoryId(15L)
                .name("우럭")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date3)
                .build();

        InventoryEntity inventory16 = InventoryEntity.builder()
                .inventoryId(16L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date5)
                .build();

        InventoryEntity inventory17 = InventoryEntity.builder()
                .inventoryId(17L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date4)
                .build();

        InventoryEntity inventory18 = InventoryEntity.builder()
                .inventoryId(18L)
                .name("장어")
                .category("활어")
                .quantity(60)
                .country("일본")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date7)
                .build();

        InventoryEntity inventory19 = InventoryEntity.builder()
                .inventoryId(19L)
                .name("광어")
                .category("활어")
                .quantity(60)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date5)
                .build();

        InventoryEntity inventory20 = InventoryEntity.builder()
                .inventoryId(20L)
                .name("문어")
                .category("활어")
                .quantity(40)
                .country("국산")
                .naturalStatus("자연")
                .companyId(101L)
                .incomingDate(date2)
                .build();

        InventoryEntity inventory21 = InventoryEntity.builder()
                .inventoryId(21L)
                .name("장어")
                .category("활어")
                .quantity(23)
                .country("국산")
                .naturalStatus("양식")
                .companyId(101L)
                .incomingDate(date2)
                .build();

        inventoryJpaRepository.save(inventory01);
        inventoryJpaRepository.save(inventory02);
        inventoryJpaRepository.save(inventory03);
        inventoryJpaRepository.save(inventory04);
        inventoryJpaRepository.save(inventory05);
        inventoryJpaRepository.save(inventory06);
        inventoryJpaRepository.save(inventory07);
        inventoryJpaRepository.save(inventory08);
        inventoryJpaRepository.save(inventory09);
        inventoryJpaRepository.save(inventory10);
        inventoryJpaRepository.save(inventory11);
        inventoryJpaRepository.save(inventory12);
        inventoryJpaRepository.save(inventory13);
        inventoryJpaRepository.save(inventory14);
        inventoryJpaRepository.save(inventory15);
        inventoryJpaRepository.save(inventory16);
        inventoryJpaRepository.save(inventory17);
        inventoryJpaRepository.save(inventory18);
        inventoryJpaRepository.save(inventory19);
        inventoryJpaRepository.save(inventory20);
        inventoryJpaRepository.save(inventory21);

        // expect
        mockMvc.perform(get("/inventories")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("search", ""))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/inventories/details")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("name", "광어")
                        .param("category", "활어"))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/inventories")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("search", "광어"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
