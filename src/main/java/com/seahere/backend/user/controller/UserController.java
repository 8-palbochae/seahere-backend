package com.seahere.backend.user.controller;

import com.seahere.backend.user.exception.DuplicateEmailException;
import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;
import com.seahere.backend.user.request.OAuthSignupReq;
import com.seahere.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/customer")
    public ResponseEntity<Void> customerAdd(@RequestBody @Valid CustomerSignupReq customerSignupReq){
        if(userService.validateEmail(customerSignupReq.getEmail())){
            throw new DuplicateEmailException();
        }

        userService.signupCustomer(customerSignupReq);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @PostMapping("/users/broker")
    public ResponseEntity<Void> brokerSignup(@RequestBody @Valid BrokerSignupReq brokerSignupReq){
        if(userService.validateEmail(brokerSignupReq.getEmail())){
            throw new DuplicateEmailException();
        }

        userService.signupBroker(brokerSignupReq);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @PostMapping("/users/ceo")
    public ResponseEntity<Void> ceoSignup(@RequestBody @Valid CeoSignupReq ceoSignupReq){
        if(userService.validateEmail(ceoSignupReq.getEmail())){
            throw new DuplicateEmailException();
        }

        userService.signupCeo(ceoSignupReq);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @PostMapping("/users/oauth")
    public ResponseEntity<Void> oauthSignup(@RequestBody OAuthSignupReq oAuthSignupReq){
        userService.signupOauth(oAuthSignupReq);
        return ResponseEntity.created(URI.create("/login")).build();
    }

    @PostMapping("/users/validation")
    public ResponseEntity<Map<String, Object>> validateEmail(@RequestBody String email){
        Map<String, Object> response = new HashMap<>();
        boolean isAvailable = !userService.validateEmail(email);

        response.put("available", isAvailable);
        response.put("message", isAvailable ? "이메일을 사용할 수 있습니다." : "이메일이 이미 사용 중입니다.");

        return ResponseEntity.ok(response);
    }
}
