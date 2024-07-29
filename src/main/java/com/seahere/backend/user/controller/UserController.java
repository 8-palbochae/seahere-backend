package com.seahere.backend.user.controller;

import com.seahere.backend.user.exception.DuplicateEmailException;
import com.seahere.backend.user.request.CustomerSignupReq;
import com.seahere.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/customer")
    public ResponseEntity<Void> customerAdd(@RequestBody CustomerSignupReq customerSignupReq){
        if(!userService.validateEmail(customerSignupReq.getEmail())){
            throw new DuplicateEmailException();
        }

        userService.signupCustomer(customerSignupReq);
        return ResponseEntity.created(URI.create("/login")).build();
    }
}
