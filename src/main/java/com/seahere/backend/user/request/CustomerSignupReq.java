package com.seahere.backend.user.request;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.common.entity.Role;
import com.seahere.backend.common.entity.SocialType;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.domain.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerSignupReq {
    private String email;
    private String password;
    private String username;
    private Address address;
    private SocialType socialType;
    private String socialId;

    @Builder
    public CustomerSignupReq(String email, String password, String username, Address address, SocialType socialType, String socialId) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.socialType = socialType;
        this.socialId = socialId;
    }

    public UserEntity to(){
        return UserEntity.builder()
                .email(email)
                .password(password)
                .username(username)
                .address(address)
                .socialType(socialType)
                .socialId(socialId)
                .role(Role.CUSTOMER)
                .status(UserStatus.APPROVED)
                .leave(false)
                .build();
    }
}
