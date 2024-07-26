package com.seahere.backend.user.request;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.common.entity.SocialType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupReq {
    private String email;
    private String password;
    private String username;
    private Address address;
    private Boolean oAuth;
    private SocialType socialType;
    private String socialId;

    @Builder
    public SignupReq(String email, String password, String username, Address address, Boolean oAuth, SocialType socialType, String socialId) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.oAuth = oAuth;
        this.socialType = socialType;
        this.socialId = socialId;
    }
}
