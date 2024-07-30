package com.seahere.backend.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seahere.backend.auth.controller.response.AuthenticationRes;
import com.seahere.backend.auth.jwt.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AuthController {
    @Value("${jwt.secretKey}")
    private String secretKey;
    @GetMapping("/authentication/protected")
    public ResponseEntity<AuthenticationRes> authenticationTokenGet(HttpServletRequest request){
        String accessToken = CookieUtil.getAccessTokenFromCookies(request);
        String refreshToken = CookieUtil.getRefreshTokenFromCookie(request);

        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken);
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(refreshToken);

            AuthenticationRes authenticationRes = AuthenticationRes.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(authenticationRes);

        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthenticationRes());
        }

    }
}
