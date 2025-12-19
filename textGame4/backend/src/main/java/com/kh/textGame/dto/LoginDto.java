package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 로그인 요청 시 클라이언트로부터 전달받는 데이터를 담는 DTO입니다.
 * 로그인 API의 `@RequestBody`에 매핑되어 사용됩니다.
 *
 * @Getter: 모든 필드의 getter 메소드를 자동으로 생성합니다.
 * @Setter: 모든 필드의 setter 메소드를 자동으로 생성합니다.
 */
@Getter
@Setter
public class LoginDto {

    /**
     * 사용자 아이디 (또는 로그인에 사용되는 이름).
     * ✨ 제안: Bean Validation을 사용하여 아이디가 비어있지 않은지 검증합니다.
     */
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    /**
     * 사용자 비밀번호.
     * ✨ 제안: Bean Validation을 사용하여 비밀번호가 비어있지 않은지 검증합니다.
     */
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. Bean Validation 적용:
 *    - `userId`와 `password` 필드는 로그인을 위해 반드시 필요한 정보이므로, `null`이나 빈 문자열이 들어오는 것을 막아야 합니다.
 *    - `jakarta.validation.constraints.NotBlank` 어노테이션을 사용하여 필드가 비어있지 않도록 강제할 수 있습니다. (코드에 반영)
 *    - `AuthController`의 로그인 메소드에서 `@Valid @RequestBody LoginDto loginDto` 와 같이 `@Valid` 어노테이션을 추가하면,
 *      이 DTO에 대한 유효성 검사가 자동으로 수행됩니다. 만약 유효성 검사를 통과하지 못하면
 *      `MethodArgumentNotValidException`이 발생하며, 이를 전역 예외 처리기(@RestControllerAdvice)에서 잡아
 *      클라이언트에게 400 Bad Request 응답과 함께 명확한 에러 메시지를 전달할 수 있습니다.
 *
 * 2. 불변성(Immutability) 고려:
 *    - 이 DTO는 오직 클라이언트의 요청 데이터를 서버로 전달하는 데만 사용됩니다.
 *      서버 내부에서 이 객체의 상태가 변경될 일은 없습니다.
 *    - `@Setter`를 제거하고, `@AllArgsConstructor`나 `@RequiredArgsConstructor`를 사용하여
 *      생성자를 통해 값을 주입받는 불변 객체로 만드는 것이 더 안전하고 예측 가능합니다.
 *    - 불변 객체는 여러 스레드에서 동시에 접근해도 안전(thread-safe)하다는 장점도 있습니다.
 *
 * 3. 필드명 일관성:
 *    - `SignUpDto`에서는 `username`을 사용하고 `LoginDto`에서는 `userId`를 사용하고 있습니다.
 *      기능상 동일한 대상을 가리킨다면 필드명을 `username`으로 통일하는 것이 API의 일관성을 높여줍니다.
 *      클라이언트 개발자가 혼동할 여지를 줄일 수 있습니다.
 *
 * 4. DTO의 목적 명확화:
 *    - 클래스 이름을 `LoginRequestDto`와 같이 'Request'를 명시적으로 붙여주면,
 *      이 DTO가 요청(Request) 처리에 사용되는 객체임을 더 명확하게 나타낼 수 있습니다.
 *      이는 `LoginResponseDto`와 같은 응답 DTO와 구분하는 데 도움이 됩니다.
 */
