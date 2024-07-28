package com.seahere.backend.company.request;

import com.seahere.backend.common.entity.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyCreateReq {
    private String registrationNumber;
    private String representativeName;
    private String companyName;
    private Address address;
    private String profileImage;

    @Builder
    public CompanyCreateReq(String registrationNumber, String representativeName, String companyName, Address address, String profileImage) {
        this.registrationNumber = registrationNumber;
        this.representativeName = representativeName;
        this.companyName = companyName;
        this.address = address;
        this.profileImage = profileImage;
    }
}
