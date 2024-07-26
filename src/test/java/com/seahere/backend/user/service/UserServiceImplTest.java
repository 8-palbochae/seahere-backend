package com.seahere.backend.user.service;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.SignupReq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class UserServiceImplTest{
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    
    @Test
    @DisplayName("SignupReq 클래스를 통해서 회워 가입이 가능하다")
    void test1() throws Exception{
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("메인 주소")
                .subAddress("상세 주소")
                .build();

        SignupReq signupReq = SignupReq.builder()
                .email("test@test.com")
                .username("여보소")
                .password("1234")
                .oAuth(false)
                .address(address)
                .build();
        //when
        Long userId = userService.signupUser(signupReq);

        //then
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        assertEquals(findUser.getId(),userId);
        assertEquals(findUser.getEmail(),signupReq.getEmail());
        assertEquals(findUser.getPassword(),signupReq.getPassword());
        assertFalse(signupReq.getOAuth());
    }
}

