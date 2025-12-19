package com.kh.textGame.controller;

import com.kh.textGame.dto.JwtResponseDto;
import com.kh.textGame.dto.LoginDto;
import com.kh.textGame.dto.SignUpDto;
import com.kh.textGame.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController: 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
 *                  - @Controller와 @ResponseBody 어노테이션이 합쳐진 형태입니다.
 *                  - 이 클래스의 모든 메소드는 뷰(View)를 반환하는 대신, HTTP 응답 본문(Response Body)에 직접
 *                    데이터(주로 JSON 또는 XML)를 작성하여 반환합니다.
 *
 * @RequestMapping("/api/auth"): 이 컨트롤러의 모든 핸들러 메소드에 대한 기본 URL 경로를 지정합니다.
 *                             - `/api/auth`로 시작하는 모든 요청은 이 컨트롤러로 라우팅됩니다.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 생성자 기반 의존성 주입(Constructor-based Dependency Injection)입니다.
     * Spring 컨테이너가 AuthService 타입의 빈(Bean)을 자동으로 찾아 이 생성자에 주입해줍니다.
     * - @Autowired를 생략할 수 있습니다. (Spring 4.3 이상)
     * - `final` 키워드를 사용하여 `authService`가 변경 불가능(immutable)하도록 보장하며,
     *   이는 런타임 시에 NullPointerException을 방지하는 데 도움이 됩니다.
     *
     * @param authService 주입받을 인증 서비스 객체
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 사용자 회원가입을 처리하는 엔드포인트입니다.
     *
     * @PostMapping("/signup"): HTTP POST 메소드의 `/api/auth/signup` 경로 요청을 이 메소드와 매핑합니다.
     * @RequestBody SignUpDto signUpDto: HTTP 요청의 본문(Body)에 포함된 JSON 데이터를 `SignUpDto` 객체로 변환(역직렬화)하여 받습니다.
     *                                   - 프론트엔드에서 보내는 JSON의 키(key)와 DTO의 필드명이 일치해야 합니다.
     * @return ResponseEntity<?>: HTTP 응답을 나타내는 객체입니다. 상태 코드, 헤더, 본문을 포함할 수 있습니다.
     *                         - `?`는 와일드카드로, 응답 본문의 타입이 상황에 따라 달라질 수 있음을 의미합니다.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        try {
            // AuthService의 signup 메소드를 호출하여 회원가입 로직을 수행합니다.
            authService.signup(signUpDto);
            // 성공 시, HTTP 200 OK 상태 코드와 함께 성공 메시지를 담은 응답을 반환합니다.
            return ResponseEntity.ok("회원가입에 성공했습니다!");
        } catch (RuntimeException e) {
            // 회원가입 중 예외(예: 중복된 아이디)가 발생하면,
            // HTTP 400 Bad Request 상태 코드와 함께 예외 메시지를 담은 응답을 반환합니다.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 사용자 로그인을 처리하고 JWT 토큰을 발급하는 엔드포인트입니다.
     *
     * @PostMapping("/login"): HTTP POST 메소드의 `/api/auth/login` 경로 요청을 이 메소드와 매핑합니다.
     * @RequestBody LoginDto loginDto: 요청 본문의 JSON 데이터를 `LoginDto` 객체로 받습니다.
     * @return ResponseEntity<?>: 성공 시 JWT 토큰을, 실패 시 에러 메시지를 담은 응답을 반환합니다.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            // AuthService의 login 메소드를 호출하여 로그인 인증 및 JWT 토큰 생성을 위임합니다.
            String token = authService.login(loginDto);
            // 성공 시, 생성된 JWT 토큰을 `JwtResponseDto`에 담아
            // HTTP 200 OK 상태 코드와 함께 JSON 형태로 반환합니다.
            return ResponseEntity.ok(new JwtResponseDto(token));
        } catch (Exception e) {
            // 로그인 실패(예: 아이디 또는 비밀번호 불일치) 시,
            // HTTP 401 Unauthorized 상태 코드와 함께 에러 메시지를 담은 응답을 반환합니다.
            return ResponseEntity.status(401).body("아이디 혹은 비밀번호가 옳지 않습니다.");
        }
    }
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 전역 예외 처리기(Global Exception Handler) 도입:
 *    - 현재 각 메소드 내에서 `try-catch` 블록을 사용하여 예외를 개별적으로 처리하고 있습니다.
 *    - 프로젝트가 커지면 중복 코드가 많아지고 유지보수가 어려워집니다.
 *    - `@RestControllerAdvice`와 `@ExceptionHandler` 어노테이션을 사용하여 전역 예외 처리 클래스를 만들면,
 *      컨트롤러 전반에서 발생하는 예외를 한 곳에서 공통으로 처리할 수 있습니다.
 *    - 예를 들어, `UserAlreadyExistsException` 같은 커스텀 예외를 정의하고, 예외 처리기에서 이 예외를 잡아
 *      적절한 HTTP 상태 코드(예: 409 Conflict)와 에러 응답 객체를 반환하도록 구현할 수 있습니다.
 *
 * 2. DTO 유효성 검사(Validation):
 *    - `@RequestBody`로 받는 DTO에 대해 유효성 검사를 추가하는 것이 좋습니다.
 *    - `spring-boot-starter-validation` 의존성을 추가하고, DTO 필드에 `@NotBlank`, `@Size`, `@Email` 등의
 *      어노테이션을 사용하여 제약조건을 명시할 수 있습니다.
 *    - 컨트롤러 메소드 파라미터에 `@Valid` 어노테이션을 추가하면, DTO 유효성 검사에 실패했을 때
 *      `MethodArgumentNotValidException`이 발생하며, 이를 전역 예외 처리기에서 잡아 처리할 수 있습니다.
 *    - 예: public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto) { ... }
 *
 * 3. 응답 객체 통일:
 *    - API 응답의 형식을 일관되게 통일하는 것이 클라이언트(프론트엔드) 개발에 도움이 됩니다.
 *    - 성공/실패 여부, 데이터, 메시지 등을 포함하는 공통 응답 DTO를 설계하고 사용하는 것이 좋습니다.
 *    - 예:
 *      public class ApiResponse<T> {
 *          private boolean success;
 *          private T data;
 *          private String message;
 *      }
 *      return ResponseEntity.ok(new ApiResponse<>(true, new JwtResponseDto(token), "Login successful"));
 *
 * 4. 좀 더 구체적인 HTTP 상태 코드 사용:
 *    - 회원가입 성공 시, 리소스가 성공적으로 생성되었음을 의미하는 HTTP 201 Created 상태 코드를 사용하는 것이
 *      RESTful 원칙에 더 부합합니다.
 *    - `ResponseEntity.created(location).body(...)` 와 같이 사용하여 생성된 리소스의 URI를 Location 헤더에
 *      포함시켜 반환할 수 있습니다.
 */
