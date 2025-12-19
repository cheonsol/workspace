package com.kh.textGame.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * JWT(JSON Web Token) 관련 유틸리티 클래스입니다.
 * 토큰 생성, 파싱, 유효성 검증 등의 핵심 기능을 담당합니다.
 *
 * @Component: 이 클래스를 Spring 컨테이너가 관리하는 빈(Bean)으로 등록합니다.
 *             다른 컴포넌트(예: `JwtAuthenticationFilter`, `AuthService`)에서 이 클래스를 주입받아 사용할 수 있습니다.
 */
@Component
public class JwtUtil {

    // ✨ 제안: 시크릿 키와 만료 시간은 하드코딩하는 대신, `application.yml` 같은 설정 파일로 분리하여 관리하는 것이 훨씬 안전하고 유연합니다.
    @Value("${jwt.secret}")
    private String secretString;

    @Value("${jwt.expiration}")
    private long expiration; // 토큰 만료 시간 (ms 단위)

    private SecretKey secretKey;

    /**
     * @PostConstruct: 의존성 주입이 완료된 후 초기화를 수행하는 메소드입니다.
     *                 `secretString` 값을 사용하여 `SecretKey` 객체를 생성합니다.
     */
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 주어진 사용자 이름을 기반으로 JWT를 생성합니다.
     *
     * @param username 토큰에 포함될 사용자 이름 (subject)
     * @return 생성된 JWT 문자열
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // 토큰의 주체(subject)로 사용자 이름을 설정합니다.
                .issuedAt(new Date(System.currentTimeMillis())) // 토큰 발급 시간을 설정합니다.
                .expiration(new Date(System.currentTimeMillis() + expiration)) // 토큰 만료 시간을 설정합니다.
                .signWith(secretKey) // 시크릿 키를 사용하여 토큰에 서명합니다. 이 서명으로 토큰의 위변조 여부를 검증합니다.
                .compact(); // 토큰을 문자열 형태로 직렬화합니다.
    }

    /**
     * 토큰에서 사용자 이름(subject)을 추출합니다.
     *
     * @param token 파싱할 JWT
     * @return 추출된 사용자 이름
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 토큰에서 만료 시간을 추출합니다.
     *
     * @param token 파싱할 JWT
     * @return 추출된 만료 시간
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 토큰에서 특정 클레임(Claim)을 추출하는 범용 메소드입니다.
     *
     * @param token          파싱할 JWT
     * @param claimsResolver 클레임에서 원하는 정보를 추출하는 함수
     * @return 추출된 정보
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 토큰의 모든 클레임을 추출합니다.
     * 이 과정에서 토큰의 서명을 검증합니다. 서명이 유효하지 않으면 예외가 발생합니다.
     *
     * @param token 파싱할 JWT
     * @return 추출된 클레임 객체
     */
    private Claims extractAllClaims(String token) {
        // ✨ 제안: 파싱 과정에서 발생할 수 있는 다양한 예외를 처리하는 것이 좋습니다.
        try {
            return Jwts.parser()
                    .verifyWith(secretKey) // 서명 검증에 사용할 시크릿 키 설정
                    .build()
                    .parseSignedClaims(token) // 토큰 파싱 및 서명 검증
                    .getPayload(); // 클레임(본문) 부분 반환
        } catch (SignatureException e) {
            // log.error("Invalid JWT signature: {}", e.getMessage());
            throw new RuntimeException("잘못된 JWT 서명입니다.");
        } catch (MalformedJwtException e) {
            // log.error("Invalid JWT token: {}", e.getMessage());
            throw new RuntimeException("유효하지 않은 JWT 토큰입니다.");
        } catch (ExpiredJwtException e) {
            // log.error("JWT token is expired: {}", e.getMessage());
            throw new RuntimeException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            // log.error("JWT token is unsupported: {}", e.getMessage());
            throw new RuntimeException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            // log.error("JWT claims string is empty: {}", e.getMessage());
            throw new RuntimeException("JWT 클레임이 비어있습니다.");
        }
    }

    /**
     * 토큰이 만료되었는지 확인합니다.
     *
     * @param token 검증할 JWT
     * @return 만료되었으면 true, 아니면 false
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 토큰이 유효한지 검증합니다.
     * 사용자 이름이 일치하고, 토큰이 만료되지 않았는지 확인합니다.
     *
     * @param token       검증할 JWT
     * @param userDetails Spring Security의 `UserDetails` 객체
     * @return 유효하면 true, 아니면 false
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 설정 외부화 (@Value):
 *    - JWT 시크릿 키와 만료 시간을 Java 코드에 하드코딩하는 것은 매우 위험합니다.
 *    - `application.yml` (또는 `application.properties`) 파일로 옮기고 `@Value` 어노테이션을 사용하여 주입받아야 합니다. (코드에 반영)
 *      ```yaml
 *      jwt:
 *        secret: "YourVeryLongAndSecureSecretKeyGoesHereYourVeryLongAndSecureSecretKeyGoesHere" # 256비트 이상의 아주 긴 시크릿 키
 *        expiration: 36000000 # 10시간 (밀리초 단위)
 *      ```
 *    - 이렇게 하면 코드를 재컴파일하지 않고도 설정을 변경할 수 있으며, Git에 시크릿 키가 노출되는 것을 방지할 수 있습니다.
 *
 * 2. 예외 처리 강화:
 *    - `extractAllClaims` 메소드에서 JWT 파싱 시 다양한 예외(`ExpiredJwtException`, `SignatureException` 등)가 발생할 수 있습니다.
 *    - 각 예외를 개별적으로 `catch`하여 로그를 남기고, 상황에 맞는 커스텀 예외를 던지거나 명확한 에러 메시지를 반환하도록 처리하면
 *      디버깅 및 에러 추적이 훨씬 용이해집니다. (코드에 예시 반영)
 *
 * 3. 역할(Role) 정보 포함:
 *    - 토큰을 생성할 때 `Jwts.builder().claim("roles", userRoles)`와 같이 사용자의 역할 정보를 클레임에 추가할 수 있습니다.
 *    - 필터에서는 이 역할 정보를 추출하여 Spring Security의 `Authentication` 객체에 `GrantedAuthority`로 설정해줄 수 있습니다.
 *    - 이렇게 하면 DB를 다시 조회하지 않고도 토큰만으로 사용자의 권한을 알 수 있어, 역할 기반 접근 제어(@PreAuthorize)를 효율적으로 수행할 수 있습니다.
 *
 * 4. Refresh Token 메커니즘 도입:
 *    - Access Token의 유효 기간을 짧게(예: 15분~1시간) 설정하고, 이와 별도로 유효 기간이 긴(예: 7일) Refresh Token을 발급하는 것이 보안상 더 안전합니다.
 *    - Access Token이 만료되면, 클라이언트는 Refresh Token을 사용하여 새로운 Access Token을 조용히 재발급받을 수 있습니다.
 *    - 이를 위해서는 Refresh Token을 저장하고 검증하는 별도의 로직이 필요합니다.
 */
