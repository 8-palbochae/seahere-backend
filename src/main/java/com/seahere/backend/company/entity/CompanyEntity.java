package com.seahere.backend.company.entity;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.company.request.CompanyCreateReq;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private String companyName;

    @Embedded
    private Address address;

    private String profileImage;

    @Builder
    public CompanyEntity(Long id, String registrationNumber, String companyName, Address address, String profileImage) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.companyName = companyName;
        this.address = address;
        this.profileImage = profileImage;
    }

    public static CompanyEntity from(CompanyCreateReq companyCreateReq){
        return CompanyEntity.builder()
                .registrationNumber(companyCreateReq.getRegistrationNumber())
                .companyName(companyCreateReq.getCompanyName())
                .address(companyCreateReq.getAddress())
                .build();
    }

    public void editProfileImage(String profileImage){
        this.profileImage = profileImage;
    }
}
