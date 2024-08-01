package com.seahere.backend.auth.oauth;

import com.seahere.backend.common.entity.Role;
import com.seahere.backend.user.domain.UserStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private Role role;
    private UserStatus status;
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, Role role, UserStatus status) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
