package com.seahere.backend.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seahere.backend.auth.filter.CustomClientBranchFilter;
import com.seahere.backend.auth.jwt.filter.JwtAuthenticationProcessingFilter;
import com.seahere.backend.auth.jwt.service.JwtService;
import com.seahere.backend.auth.login.filter.CustomJsonUsernamePasswordAuthenticationFilter;
import com.seahere.backend.auth.login.handler.CustomLogoutSuccessHandler;
import com.seahere.backend.auth.login.handler.LoginFailureHandler;
import com.seahere.backend.auth.login.handler.LoginSuccessHandler;
import com.seahere.backend.auth.login.service.LoginService;
import com.seahere.backend.auth.oauth.handler.OAuth2LoginFailureHandler;
import com.seahere.backend.auth.oauth.handler.OAuth2LoginSuccessHandler;
import com.seahere.backend.auth.oauth.service.CustomOAuth2UserService;
import com.seahere.backend.redis.respository.TokenRepository;
import com.seahere.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final LoginService loginService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenRepository tokenRepository;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, ServletRegistrationBean h2Console) throws Exception {
         http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .cors().and()
            .headers().frameOptions().disable()
            .and()
                 .authorizeRequests()
                 .antMatchers("/h2-console/**").permitAll()
                 .antMatchers("/swagger-ui/**").permitAll()
                 .antMatchers(HttpMethod.POST, "/login").permitAll()
                 .antMatchers(HttpMethod.POST, "/ocr").permitAll()
                 .antMatchers(HttpMethod.POST,"/companies").permitAll()
                 .antMatchers(HttpMethod.POST,"/companies/duplicate").permitAll()
                 .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                 .antMatchers("/v3/api-docs").permitAll() // Swagger UI 접근 허용
                 .antMatchers("/swagger/**").permitAll()
                 .antMatchers("/authentication/protected").permitAll()// 모든 메서드 허용
                 .anyRequest().authenticated()
                 .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                 .and()
                 .logout()
                 .logoutUrl("/logout")  // 로그아웃 요청을 받을 URL
                .logoutSuccessHandler(logoutSuccessHandler())  // 커스텀 로그아웃 성공 핸들러 설정
                .deleteCookies("JSESSIONID")  // 쿠키 삭제
                .invalidateHttpSession(true)  // 세션 무효화
                .and()
            .oauth2Login()
            .successHandler(oAuth2LoginSuccessHandler)
            .failureHandler(oAuth2LoginFailureHandler)
            .userInfoEndpoint().userService(customOAuth2UserService);
         http.addFilterBefore(customClientFilter(), OAuth2AuthorizationRequestRedirectFilter.class);
         http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
         http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:3000",
                        "http://localhost:3000",
                        "http://localhost:5173",
                        "https://10.10.10.37:3000",
                        "http://10.10.10.170:5173",
                        "http://172.18.117.115:3000",
                        "https://10.10.10.24:3000",
                        "http://10.10.10.24:5173"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Authorization-refresh");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService, userRepository,tokenRepository);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(tokenRepository);
    }

    @Bean
    public CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordAuthenticationFilter() {
        CustomJsonUsernamePasswordAuthenticationFilter customJsonUsernamePasswordLoginFilter
                = new CustomJsonUsernamePasswordAuthenticationFilter(objectMapper);
        customJsonUsernamePasswordLoginFilter.setAuthenticationManager(authenticationManager());
        customJsonUsernamePasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessHandler());
        customJsonUsernamePasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return customJsonUsernamePasswordLoginFilter;
    }

    @Bean
    public CustomClientBranchFilter customClientFilter(){
        CustomClientBranchFilter customClientFilter = new CustomClientBranchFilter(oAuth2LoginSuccessHandler,oAuth2LoginFailureHandler);
        return customClientFilter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter jwtAuthenticationFilter = new JwtAuthenticationProcessingFilter(jwtService, userRepository,tokenRepository    );
        return jwtAuthenticationFilter;
    }
}
