package com.seahere.backend.user.domain;

import com.seahere.backend.common.entity.Address;
import com.seahere.backend.common.entity.Role;
import com.seahere.backend.common.entity.SocialType;
import com.seahere.backend.user.request.SignupReq;
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
    private Long id;

    private String username;

    private String email;

    private String password;

    @Embedded
    private Address address;

    private Boolean leave;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;
    private String profileImage;
    private String refreshToken;

    @Builder
    public UserEntity(Long id, String username, String email, String password, Address address, Boolean leave, Role role, SocialType socialType, String socialId, String profileImage, String refreshToken) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.leave = leave;
        this.role = role;
        this.socialType = socialType;
        this.socialId = socialId;
        this.profileImage = profileImage;
        this.refreshToken = refreshToken;
    }

    public void authorizeUser() {
        this.role = Role.CUSTOMER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    public static UserEntity from(SignupReq signupReq){
        return UserEntity.builder()
                .email(signupReq.getEmail())
                .password(signupReq.getPassword())
                .leave(false)
                .role(Role.CUSTOMER)
                .socialType(signupReq.getSocialType())
                .socialId(signupReq.getSocialId())
                .username(signupReq.getUsername())
                .address(signupReq.getAddress())
                .build();
    }
}
