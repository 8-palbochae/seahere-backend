package com.seahere.backend.user.domain;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.common.entity.Role;
import com.seahere.backend.common.entity.SocialType;
import com.seahere.backend.company.entity.CompanyEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    private String username;

    private String email;

    private String password;

    @Embedded
    private Address address;

    private String telNumber;

    private Boolean leave;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String profileImage;

    private String refreshToken;

    @Builder
    public UserEntity(Long id, CompanyEntity company, String username, String email, String password, Address address, String telNumber, Boolean leave, Role role, SocialType socialType, String socialId, UserStatus status, String profileImage, String refreshToken) {
        this.id = id;
        this.company = company;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.telNumber = telNumber;
        this.leave = leave;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
        this.status = status;
        this.profileImage = profileImage;
        this.refreshToken = refreshToken;
    }

    public void authorizeCustomer() {
        this.role = Role.CUSTOMER;
    }

    public void authorizeAdmin() {
        this.role = Role.ADMIN;
    }

    public void authorizeEmployee() {
        this.role = Role.EMPLOYEE;
    }

    public void signupOAuth(String username, Address address,String telNumber){
        this.username=username;
        this.address=address;
        this.telNumber = telNumber;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public void updateCompany(CompanyEntity company){
        this.company = company;
    }

    public void editRole(Role role){
        this.role  = role;
    }

    public void editStatus(UserStatus status){
        this.status = status;
    }
}
