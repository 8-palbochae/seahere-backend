package com.seahere.backend.auth.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.seahere.backend.auth.jwt.exception.ValidateTokenException;
import com.seahere.backend.auth.jwt.service.JwtService;
import com.seahere.backend.auth.jwt.util.CookieUtil;
import com.seahere.backend.auth.service.AuthService;
import com.seahere.backend.company.response.CompanyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    @Value("${jwt.secretKey}")
    private String secretKey;
    private final JwtService jwtService;
    private final AuthService authService;

    @GetMapping("/authentication/protected")
    public ResponseEntity<CompanyResponse> authenticationTokenGet(HttpServletRequest request) {
        String accessToken = CookieUtil.getAccessTokenFromCookies(request);
        String refreshToken = CookieUtil.getRefreshTokenFromCookie(request);

        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(accessToken);
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(refreshToken);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", accessToken);
            headers.set("Authorization-Refresh", refreshToken);

            String email = jwtService.extractEmail(accessToken)
                    .orElseThrow(ValidateTokenException::new);
            CompanyResponse company = authService.getSocialUserWithCompany(email);

            return new ResponseEntity<>(company, headers, HttpStatus.OK);

        } catch (Exception e) {
            throw new ValidateTokenException();
        }
    }

    @PostMapping("/auth/token")
    public ResponseEntity<Void> accessTokenGet(HttpServletRequest request){
        return ResponseEntity.ok(null);
    }
}