package com.seahere.backend.auth.oauth.handler;

import com.seahere.backend.user.exception.BrokerPermissionException;
import com.seahere.backend.user.exception.UserNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {
    @Value("${client.server.address}")
    private String CLIENT_SERVER_ADDRESS;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        if (exception.getCause() instanceof BrokerPermissionException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\": \"사용자 계정이 활성화되지 않았습니다.\"}");
            response.sendRedirect(CLIENT_SERVER_ADDRESS +"/login");
        } else if (exception.getCause() instanceof UserNotFound) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"message\": \"사용자를 찾을 수 없습니다.\"}");
            response.sendRedirect(CLIENT_SERVER_ADDRESS +"/login");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"로그인 실패! 이메일이나 비밀번호를 확인해주세요.\"}");
            response.sendRedirect(CLIENT_SERVER_ADDRESS +"/login");
        }

        log.info("로그인에 실패했습니다. 메시지 : {}", exception.getMessage());
    }
}
