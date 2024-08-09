package com.seahere.backend.auth.oauth.handler;

import com.seahere.backend.auth.jwt.service.JwtService;
import com.seahere.backend.auth.oauth.CustomOAuth2User;
import com.seahere.backend.common.entity.Role;
import com.seahere.backend.user.domain.UserEntity;
import com.seahere.backend.user.exception.UserNotFound;
import com.seahere.backend.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
@Getter
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${client.broker.port}")
    private String BROKER_PORT;
    @Value("${client.customer.port}")
    private String CUSTOMER_PORT;
    @Value("${client.broker.redirect}")
    private String BROKER_REDIRECT;
    @Value("${client.customer.redirect}")
    private String CUSTOMER_REDIRECT;


    private String responseServer;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            if(oAuth2User.getUser().getRole() == Role.GUEST) {
                String accessToken = jwtService.createAccessToken(oAuth2User.getUser().getEmail());
                UserEntity findUser = userRepository.findByEmail(oAuth2User.getUser().getEmail())
                        .orElseThrow(UserNotFound::new);
                response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);

                if(responseServer.contains(CUSTOMER_PORT)){
                    response.sendRedirect(responseServer + CUSTOMER_REDIRECT + "?guest=" + findUser.getId());
                }
                else{
                    response.sendRedirect(responseServer + BROKER_REDIRECT + "?guest=" + findUser.getId());
                }

                jwtService.sendAccessAndRefreshToken(response, accessToken, null);
            } else {
                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
            }
        } catch (Exception e) {
            throw e;
        }

    }

    // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.getUser().getEmail());
        String refreshToken = jwtService.createRefreshToken();
        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(accessTokenCookie);

        // Refresh Token을 HttpOnly 쿠키에 저장
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60 * 24 * 7); // 1 week
        response.addCookie(refreshTokenCookie);

        jwtService.updateRefreshToken(oAuth2User.getUser().getEmail(), refreshToken);
        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);

        response.sendRedirect(responseServer + "loading");
    }

    public void editResponseServer(String responseServer){
        this.responseServer = responseServer;
    }
}
