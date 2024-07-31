package com.seahere.backend.user.request;

import com.seahere.backend.common.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthSignupReq {
    private Long userId;
    private String username;
    private Long companyId;
    private String type;
    private Address address;

    @Builder
    public OAuthSignupReq(Long userId, String username, Long companyId, String type, Address address) {
        this.userId = userId;
        this.username = username;
        this.companyId = companyId;
        this.type = type;
        this.address = address;
    }

    public Boolean isCeo(){
        return type.equals("ceo");
    }

    public Boolean isCustomer(){
        return type.equals("customer");
    }

    public Boolean isBroker(){
        return type.equals("broker");
    }

}
