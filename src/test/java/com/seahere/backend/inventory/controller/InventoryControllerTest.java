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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(value = "/sql/inventory-service-test.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
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
    @DisplayName("InventoryReqSearchRequest 클래스로 GET 요청시 CompanyId에 따른 전체 재고 목록이 반환된다.")
    public void test1() throws Exception{
        //given
        InventoryReqSearchRequest inventoryReqSearchRequest = InventoryReqSearchRequest.builder()
                .companyId(101L)
                .size(10)
                .page(0)
                .build();
        // expect
        mockMvc.perform(get("/inventories")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("search", ""))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("InventoryReqSearchRequest 클래스로 /detail에 GET 요청시 CompanyId, name, category 에 따른 재고 목록이 반환된다.")
    public void test2() throws Exception{
        //given
        InventoryReqSearchRequest inventoryReqSearchRequest = InventoryReqSearchRequest.builder()
                .companyId(101L)
                .size(10)
                .page(0)
                .build();

        mockMvc.perform(get("/inventories/details")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("name", "광어")
                        .param("category", "활어"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("InventoryReqSearchRequest 클래스로 GET 요청시 CompanyId, search에 따른 검색 재고 목록이 반환된다.")
    public void test3() throws Exception{
        //given
        InventoryReqSearchRequest inventoryReqSearchRequest = InventoryReqSearchRequest.builder()
                .companyId(101L)
                .size(10)
                .page(0)
                .build();

        mockMvc.perform(get("/inventories")
                        .param("companyId", "101")
                        .param("size", "10")
                        .param("page", "0")
                        .param("search", "광어"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
