package com.kh.textGame.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

/**
 * JWT(JSON Web Token) 기반 인증을 위한 Spring Security 필터입니다.
 * 모든 HTTP 요청에 대해 한 번만 실행되며(OncePerRequestFilter), 요청 헤더에서 JWT를 추출하고 유효성을 검증하여
 * Spring Security 컨텍스트에 인증 정보를 설정하는 역할을 합니다.
 *
 * @Component: 이 클래스를 Spring 컨테이너의 빈으로 등록합니다.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // ✨ 제안: 필터 내에서 발생하는 예외를 ControllerAdvice에서 처리하기 위한 HandlerExceptionResolver 주입
    //         이렇게 하면 필터 체인에서 발생한 예외도 일관된 JSON 응답으로 처리할 수 있습니다.
    // @Autowired
    // @Qualifier("handlerExceptionResolver")
    // private HandlerExceptionResolver resolver;


    /**
     * 생성자 기반 의존성 주입.
     * @param jwtUtil JWT 관련 유틸리티 (토큰 생성, 검증, 정보 추출)
     * @param userDetailsService 사용자 상세 정보를 로드하는 서비스
     */
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 모든 요청에 대해 필터링 로직을 수행합니다.
     *
     * @param request       HTTP 요청 객체
     * @param response      HTTP 응답 객체
     * @param filterChain   다음 필터로 요청을 전달하는 객체
     * @throws ServletException 서블릿 관련 예외 발생 시
     * @throws IOException      입출력 예외 발생 시
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Authorization 헤더를 추출합니다.
        final String jwt;
        final String username;

        // 1. Authorization 헤더가 없거나 "Bearer "로 시작하지 않으면 JWT가 없는 요청으로 간주하고 다음 필터로 넘깁니다.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 2. "Bearer " 부분을 제외한 순수 JWT 토큰을 추출합니다.
            jwt = authHeader.substring(7);
            // 3. JWT에서 사용자 이름(username)을 추출합니다.
            username = jwtUtil.extractUsername(jwt);

            // 4. 사용자 이름이 추출되었고, 아직 SecurityContext에 인증 정보가 없는 경우에만 인증을 진행합니다.
            //    (이미 인증된 사용자는 다시 인증하지 않도록 방지)
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 5. UserDetailsService를 통해 사용자 이름을 바탕으로 UserDetails 객체를 로드합니다.
                //    이 과정에서 DB 조회 등이 발생할 수 있습니다.
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // 6. 로드된 UserDetails와 추출된 JWT를 사용하여 토큰의 유효성을 검증합니다.
                //    (사용자 이름 일치 여부, 토큰 만료 여부 등)
                // ✨ 제안: `jwtUtil.validateToken(jwt, userDetails)`로 UserDetails 객체를 통째로 넘겨서
                //         내부적으로 username을 비교하고 roles도 활용할 수 있도록 수정하는 것이 더 좋습니다.
                if (jwtUtil.validateToken(jwt, userDetails)) { // ✨ 수정: userDetails 객체를 인자로 전달
                    // 7. 토큰이 유효하면 UsernamePasswordAuthenticationToken을 생성하여 인증 객체를 만듭니다.
                    //    `userDetails`: 인증된 사용자 정보
                    //    `null`: 자격 증명(credential)은 이미 토큰으로 검증되었으므로 null
                    //    `userDetails.getAuthorities()`: 사용자의 권한 정보 (예: ROLE_USER, ROLE_ROLE_ADMIN)
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    // 8. 요청 세부 정보(IP 주소, 세션 ID 등)를 인증 토큰에 설정합니다.
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    // 9. 생성된 인증 객체를 SecurityContext에 설정합니다.
                    //    이렇게 하면 현재 요청에 대해 사용자가 인증된 것으로 간주되며,
                    //    이후 SecurityContextHolder를 통해 언제든 인증 정보를 접근할 수 있습니다.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // ✨ 제안: JWT 유효성 검증 실패 시 발생하는 예외를 이곳에서 처리합니다.
            //         resolver.resolveException(request, response, null, e);
            //         필터 체인을 중단하고 클라이언트에게 적절한 에러 응답을 보낼 수 있습니다.
            //         현재는 단순히 로깅만 하고 다음 필터로 넘어가므로, 인증 실패 시 401 Unathorized 응답이 바로 가지 않을 수 있습니다.
            System.err.println("JWT 인증 중 오류 발생: " + e.getMessage());
            // 에러 발생 시 SecurityContext를 비워주는 것이 좋습니다.
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("JWT authentication failed: " + e.getMessage());
            return; // 중요: 에러 응답 후 필터 체인 진행 중단
        }

        // 10. 현재 필터의 처리가 완료되었으므로, 요청을 다음 필터로 전달합니다.
        filterChain.doFilter(request, response);
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 예외 처리 강화 및 전역 핸들링:
 *    - `jwtUtil.extractUsername(jwt)` 또는 `jwtUtil.validateToken` 과정에서
 *      토큰 만료(`ExpiredJwtException`), 잘못된 서명(`SignatureException`) 등의 예외가 발생할 수 있습니다.
 *    - 이러한 예외들이 필터 체인에서 발생하면 스프링의 `@ControllerAdvice`로 처리되지 않습니다.
 *    - 해결책: 필터 내에서 `try-catch` 블록으로 이 예외들을 명시적으로 잡고,
 *      `HandlerExceptionResolver`를 주입받아 예외를 `resolveException` 메소드로 넘겨주면,
 *      `@ControllerAdvice`에서 정의한 전역 예외 처리 로직으로 예외를 처리할 수 있습니다. (코드에 주석으로 제안 추가)
 *    - 또는 필터 내에서 직접 `response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());`와 같이 처리할 수도 있습니다.
 *
 * 2. UserDetails 객체 활용 개선:
 *    - `jwtUtil.validateToken(jwt, userDetails.getUsername())` 부분에서 `userDetails.getUsername()` 대신
 *      `jwtUtil.validateToken(jwt, userDetails)`로 `UserDetails` 객체를 직접 넘겨주어
 *      `JwtUtil` 내부에서 사용자 이름 비교와 더불어 `userDetails.getAuthorities()` 등을 활용할 수 있도록 개선하는 것이 좋습니다. (코드에 반영)
 *
 * 3. 로깅 추가:
 *    - `org.slf4j.Logger`를 사용하여 인증 과정에서 발생하는 상황이나 에러를 상세하게 로깅하면,
 *      문제 발생 시 원인 분석에 큰 도움이 됩니다.
 *
 * 4. Refresh Token 처리 로직 추가:
 *    - `JwtUtil`에서 제안된 바와 같이 Refresh Token을 사용하는 경우,
 *      이 필터는 Access Token의 유효성만 검사하고, Access Token이 만료되면
 *      클라이언트에게 Refresh Token을 사용하여 새로운 Access Token을 요청하도록 유도하는 로직을 추가해야 합니다.
 *      (`/api/auth/refresh` 같은 새로운 엔드포인트를 통해 처리)
 */
