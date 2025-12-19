package com.kh.textGame.config;

import com.kh.textGame.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor // 원칙 1: 필드 주입 대신 생성자 주입을 Lombok으로 자동화하여 가독성을 높임
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 원칙 2: 람다 DSL 및 메서드 참조(::) 사용
                // 커스텀 설정을 직접 넣기보다 관리되는 빈(corsConfigurationSource)을 연결하여 설정을 분리함
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 원칙 3: 무상태(Stateless) 아키텍처 설정
                // REST API는 세션을 쓰지 않으므로 CSRF를 끄고, 세션 정책을 STATELESS로 고정하여 서버 부하를 줄임
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 원칙 4: 인가 규칙의 선언적 정의
                // permitAll은 보안 검사를 건너뛰는 것이 아니라 '모두에게 허용'하는 규칙임을 명시
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/boards/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 원칙 5: 필터 체인 순서 제어
                // JWT는 ID/PW 기반 인증 전에 처리되어야 하므로 UsernamePasswordAuthenticationFilter 앞에 배치
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 원칙 6: 자동 구성(Auto-Configuration) 활용
     * AuthenticationProvider를 직접 빈으로 등록하지 않아도,
     * UserDetailsService와 PasswordEncoder가 빈으로 등록되어 있다면 Spring이 자동으로 DaoAuthenticationProvider를 생성함.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}