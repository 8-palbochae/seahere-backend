package com.seahere.backend.follow.controller;


import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.follow.controller.response.FollowReqResponse;
import com.seahere.backend.follow.entity.FollowEntity;
import com.seahere.backend.follow.service.FollowService;
import com.seahere.backend.inventory.controller.response.InventoryReqListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @GetMapping
    public ResponseEntity<List<FollowReqResponse>> followReqList(String search, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<FollowReqResponse> results = followService.findFollowingByCustomerId(customUserDetails.getUser().getUserId(), search);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/follow-company")
    public ResponseEntity followCompany(@AuthenticationPrincipal CustomUserDetails customUserDetails, Long companyId) {
        followService.save(customUserDetails.getUser().getUserId(), companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unfollow-company")
    public ResponseEntity unfollowCompany(@AuthenticationPrincipal CustomUserDetails customUserDetails, Long followId){
        followService.delete(customUserDetails.getUser().getUserId(), followId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
