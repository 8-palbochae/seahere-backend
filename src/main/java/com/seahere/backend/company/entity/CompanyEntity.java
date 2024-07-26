package com.seahere.backend.company.entity;

import com.seahere.backend.common.entity.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
