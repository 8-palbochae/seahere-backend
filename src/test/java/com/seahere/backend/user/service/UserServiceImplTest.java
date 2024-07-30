package com.seahere.backend.user.service;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.common.entity.Role;
import com.seahere.backend.company.entity.CompanyEntity;
import com.seahere.backend.company.repository.CompanyRepository;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.domain.UserStatus;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest{
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @BeforeEach
    void clear(){
        userRepository.deleteAll();
    }
    
    @Test
    @DisplayName("CustomerSignupReq 클래스를 통해서 커스터머 일반 회원 가입이 가능하다")
    void test1() throws Exception{
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("메인 주소")
                .subAddress("상세 주소")
                .build();

        CustomerSignupReq signupReq = CustomerSignupReq.builder()
                .email("test@test.com")
                .username("여보소")
                .password("1234")
                .address(address)
                .build();
        //when
        Long userId = userService.signupCustomer(signupReq);

        //then
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        assertEquals(findUser.getId(),userId);
        assertEquals(findUser.getEmail(),signupReq.getEmail());
        assertTrue(encoder.matches("1234", findUser.getPassword()));
        assertEquals(findUser.getRole(), Role.CUSTOMER);
    }

    @Test
    @DisplayName("BrokerSignup 클래스를 통해서  브로커 일반 회원 가입이 가능하고 상태는 PENDING이다")
    void test2() throws Exception{
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("메인 주소")
                .subAddress("상세 주소")
                .build();

        BrokerSignupReq signupReq = BrokerSignupReq.builder()
                .email("test@test.com")
                .username("여보소")
                .password("1234")
                .address(address)
                .build();
        //when
        Long userId = userService.signupBroker(signupReq);

        //then
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        assertEquals(findUser.getId(),userId);
        assertEquals(findUser.getEmail(),signupReq.getEmail());
        assertTrue(encoder.matches("1234", findUser.getPassword()));
        assertEquals(findUser.getRole(), Role.EMPLOYEE);
        assertEquals(findUser.getStatus(), UserStatus.PENDING);
    }

    @Transactional
    @Test
    @DisplayName("CeoSignupReq 클래스를 통해서  브로커 어드민 회원 가입이 가능하다")
    void test3() throws Exception{
        //given
        Address address = Address.builder()
                .postCode("12345")
                .mainAddress("메인 주소")
                .subAddress("상세 주소")
                .build();

        CompanyEntity company = CompanyEntity.builder()
                .companyName("여보소수산")
                .address(address)
                .registrationNumber("12345")
                .build();
        companyRepository.save(company);

        CeoSignupReq signupReq = CeoSignupReq.builder()
                .email("test@test.com")
                .username("여보소")
                .password("1234")
                .companyId(company.getId())
                .address(address)
                .build();
        //when
        Long userId = userService.signupCeo(signupReq);

        //then
        UserEntity findUser = userRepository.findById(userId).orElseThrow(UserNotFound::new);
        CompanyEntity findCompany = findUser.getCompany();
        assertEquals(findUser.getId(),userId);
        assertEquals(findUser.getEmail(),signupReq.getEmail());
        assertTrue(encoder.matches("1234", findUser.getPassword()));
        assertEquals(findUser.getRole(), Role.ADMIN);
        assertEquals(findUser.getStatus(), UserStatus.APPROVED);

        assertEquals(findCompany.getId(),company.getId());
        assertEquals(findCompany.getCompanyName(),company.getCompanyName());
        assertEquals(findCompany.getRegistrationNumber(),company.getRegistrationNumber());
    }
}

