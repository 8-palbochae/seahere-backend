package com.seahere.backend.user.controller;

import com.seahere.backend.user.request.CustomerSignupReq;
import com.seahere.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/customer")
    public ResponseEntity<Long> customerAdd(@RequestBody CustomerSignupReq customerSignupReq){
        Long savedId = userService.signupCustomer(customerSignupReq);
        return ResponseEntity.ok(savedId);
    }
}
