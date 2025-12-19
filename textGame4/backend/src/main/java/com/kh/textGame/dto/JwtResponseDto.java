package com.kh.textGame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JWT(JSON Web Token) 인증 성공 후 클라이언트에게 응답으로 전달되는 데이터를 담는 DTO입니다.
 * 주로 로그인 API의 성공 응답 본문에 사용됩니다.
 *
 * @Getter: 이 클래스의 모든 필드에 대한 public getter 메소드(`getToken()`)를 자동으로 생성합니다.
 *          - 외부에서 이 객체의 데이터에 접근할 수 있게 해줍니다.
 *          - JSON 직렬화(객체 -> JSON 문자열) 시에도 이 getter 메소드가 사용됩니다.
 *
 * @AllArgsConstructor: 모든 필드를 포함하는 생성자를 자동으로 생성합니다.
 *                      - `public JwtResponseDto(String token) { ... }`
 *                      - 이 DTO 객체를 간편하게 생성할 수 있게 해줍니다.
 */
@Getter
@AllArgsConstructor
public class JwtResponseDto {

    /**
     * 서버에서 발급한 JWT(Access Token)입니다.
     * 클라이언트는 이 토큰을 저장해두었다가, 이후 서버에 API를 요청할 때마다
     * HTTP 헤더(보통 `Authorization: Bearer <token>`)에 포함시켜 보내야 합니다.
     * 서버는 이 토큰을 통해 사용자를 식별하고 인증 상태를 확인합니다.
     */
    private String token;

    // ✨ 제안: `tokenType` 필드를 추가하여 토큰의 종류를 명시해주는 것이 좋습니다.
    // private final String tokenType = "Bearer";
    // 이는 클라이언트가 `Authorization` 헤더를 구성할 때 어떻게 사용해야 할지 알려주는 명확한 가이드가 됩니다.
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 불변성(Immutability):
 *    - 현재 `@Getter`와 `@AllArgsConstructor`를 사용하여 생성된 객체는 필드 값을 변경할 수 없는 사실상의 불변 객체입니다. (Setter가 없으므로)
 *    - `private final String token;` 과 같이 필드에 `final`을 명시적으로 추가하면, 이 객체가 불변임을 코드 상에서 더 명확하게 보장할 수 있습니다.
 *
 * 2. 추가 정보 포함:
 *    - 로그인 성공 응답 시, Access Token 외에도 클라이언트에게 유용한 추가 정보를 함께 전달할 수 있습니다.
 *    - **Refresh Token**: Access Token의 유효 기간을 짧게 설정하고(보안상 이점), 만료 시 새로운 Access Token을 발급받기 위한 Refresh Token을 함께 전달하는 것이 일반적인 인증 방식입니다.
 *                       Refresh Token은 Access Token보다 긴 유효 기간을 가집니다.
 *                       `private String refreshToken;`
 *    - **Token 만료 시간**: 클라이언트가 토큰의 만료 시점을 인지하고, 만료 전에 토큰 갱신을 요청할 수 있도록 만료 시간을 함께 전달할 수 있습니다.
 *                         `private Long expiresIn;`
 *    - **사용자 정보**: 로그인 성공 후 클라이언트가 즉시 사용자 이름이나 역할을 화면에 표시해야 할 경우,
 *                     별도의 API 요청 없이 바로 사용할 수 있도록 기본적인 사용자 정보를 포함시킬 수 있습니다.
 *                     `private String username;`
 *                     `private List<String> roles;`
 *
 *    - 개선된 DTO 예시:
 *      ```java
 *      @Getter
 *      public class LoginResponseDto {
 *          private final String accessToken;
 *          private final String refreshToken;
 *          private final String tokenType = "Bearer";
 *          private final Long expiresIn;
 *          private final String username;
 *      }
 *      ```
 *
 * 3. DTO 네이밍:
 *    - `JwtResponseDto`는 이름이 명확하지만, `LoginResponseDto` 또는 `AuthTokenDto` 와 같이 API의 기능이나 데이터의 목적에 더 초점을 맞춘 이름도 고려해볼 수 있습니다.
 */
