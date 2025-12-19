package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 회원가입 요청 시 클라이언트로부터 전달받는 데이터를 담는 DTO입니다.
 * 회원가입 API의 `@RequestBody`에 매핑되어 사용됩니다.
 *
 * @Getter/@Setter: Lombok 어노테이션. 모든 필드에 대한 getter/setter를 자동 생성합니다.
 */
@Getter
@Setter
public class SignUpDto {

    /**
     * 사용자 로그인 ID.
     * ✨ 제안: Bean Validation을 사용하여 아이디 형식과 길이를 제한합니다.
     */
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 사용 가능합니다.")
    private String userId;

    /**
     * 사용자 비밀번호.
     * 이 비밀번호는 서비스 계층으로 전달된 후, 반드시 암호화(hashing)되어 데이터베이스에 저장되어야 합니다.
     * 절대 평문(plain text)으로 저장해서는 안 됩니다.
     * ✨ 제안: Bean Validation을 사용하여 비밀번호 복잡도 규칙을 적용합니다.
     */
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하로 입력해주세요.")
    // 예: 최소 하나의 영문자, 숫자, 특수문자를 포함하는 정규식
    // @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 영문, 숫자, 특수문자를 모두 포함해야 합니다.")
    private String password;

    /**
     * 게임 내에서 사용할 닉네임.
     * ✨ 제안: Bean Validation을 사용하여 닉네임 형식과 길이를 제한하고 중복되지 않도록 검사해야 합니다.
     */
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
    private String nickname;
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. 강력한 유효성 검사 (Bean Validation):
 *    - 회원가입 데이터는 보안과 직결되므로, 서버 측에서 강력한 유효성 검사를 수행해야 합니다.
 *    - `@NotBlank`, `@Size`, `@Pattern`(정규식) 등의 어노테이션을 조합하여 각 필드의 규칙을 명확하게 정의할 수 있습니다. (코드에 예시 반영)
 *    - `AuthController`의 `registerUser` 메소드에서 `@Valid` 어노테이션을 사용하여 이 검사를 활성화해야 합니다.
 *
 * 2. 비밀번호 확인 필드 추가:
 *    - 일반적인 회원가입 UI에서는 비밀번호를 두 번 입력받아 오타를 방지합니다.
 *    - 이를 서버에서도 검증하려면 `private String passwordCheck;` 필드를 DTO에 추가하고,
 *      두 필드의 값이 일치하는지 확인하는 커스텀 유효성 검사 어노테이션을 만들거나 서비스 계층에서 직접 비교할 수 있습니다.
 *      `if (!signUpDto.getPassword().equals(signUpDto.getPasswordCheck())) { ... }`
 *
 * 3. 필드명 일관성:
 *    - `LoginDto`에서는 `userId`를 사용하고 `SignUpDto`에서도 `userId`를 사용하고 있어 일관성이 있습니다.
 *      만약 다른 DTO에서 `username` 등 다른 이름을 사용한다면 하나로 통일하는 것이 좋습니다.
 *
 * 4. 역할(Role) 필드:
 *    - 애플리케이션에 사용자(USER)와 관리자(ADMIN) 구분이 필요하다면, 회원가입 시 기본 역할을 할당하는 로직이 필요합니다.
 *    - DTO 자체에 `role` 필드를 포함시켜 관리자가 사용자를 생성할 때 역할을 지정할 수 있게 할 수도 있지만,
 *      일반 사용자가 회원가입할 때는 서비스 계층에서 자동으로 'ROLE_USER'를 부여하는 것이 일반적입니다.
 *
 * 5. 불변성(Immutability):
 *    - 요청 데이터를 담는 DTO는 서버 내에서 변경될 이유가 없으므로, `@Setter`를 제거하고
 *      생성자를 통해 값을 주입받는 불변 객체로 설계하는 것이 더 안전합니다.
 *
 * 6. DTO 명칭:
 *    - `SignUpRequestDto`와 같이 'Request' 접미사를 붙여주면 이 DTO의 용도를 더 명확하게 표현할 수 있습니다.
 */
