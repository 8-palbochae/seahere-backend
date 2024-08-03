package com.seahere.backend.user.controller;

import com.seahere.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TeamController {
    private final UserService userService;

    @PatchMapping("/teams")
    public ResponseEntity<Void> memberApprove(@RequestBody String employeeEmail, Principal principal){
        String loginEmail = principal.getName();
        userService.approveEmployee(loginEmail,employeeEmail);

        return ResponseEntity.ok(null);
    }
}
