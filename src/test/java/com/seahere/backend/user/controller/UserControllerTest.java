package com.seahere.backend.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seahere.backend.common.entity.Address;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.CustomerSignupReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    @DisplayName("CusomterSignupReq 클래스로 POST 요청시 커스터머 계정이 생성된다")
    public void test1() throws Exception{
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("부산광역시")
                .subAddress("스파로스 아카데미")
                .build();

        CustomerSignupReq signupReq = CustomerSignupReq.builder()
                .email("test@test.com")
                .username("여보소")
                .password("1234")
                .address(address)
                .build();

        String json = objectMapper.writeValueAsString(signupReq);
        //expect
        mockMvc.perform(MockMvcRequestBuilders. post("/users/customer")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}