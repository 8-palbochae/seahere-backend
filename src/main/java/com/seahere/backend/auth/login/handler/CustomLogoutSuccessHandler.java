package com.seahere.backend.auth.login.handler;


import com.seahere.backend.auth.login.CustomUserDetails;
import com.seahere.backend.redis.entity.Token;
import com.seahere.backend.redis.exception.RedisRefreshNotFound;
import com.seahere.backend.redis.respository.TokenRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutSuccessHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Transactional
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String email = userDetails.getUser().getEmail();
            Token token = tokenRepository.findByEmail(email)
                    .orElseThrow(RedisRefreshNotFound::new);

            tokenRepository.delete(token);


            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().flush();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().flush();
        }
    }
}