package com.seahere.backend.auth.controller.response;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationRes {
    private String accessToken;
    private String refreshToken;

    @Builder
    public AuthenticationRes(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
