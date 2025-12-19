package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object)는 계층 간 데이터 전송을 위해 사용되는 객체입니다.
 * 주로 클라이언트(View)와 컨트롤러(Controller), 서비스(Service)와 컨트롤러 사이에서 데이터를 주고받는 데 사용됩니다.
 * 엔티티(Entity) 객체를 직접 클라이언트에 노출하는 것은 보안 및 비즈니스 로직 노출의 위험이 있으므로,
 * 필요한 데이터만 담은 DTO를 만들어 사용하는 것이 좋습니다.
 *
 * @Getter: Lombok 어노테이션으로, 모든 필드에 대한 getter 메소드(getTitle(), getContents() 등)를 자동으로 생성합니다.
 * @Setter: Lombok 어노테이션으로, 모든 필드에 대한 setter 메소드(setTitle(), setContents() 등)를 자동으로 생성합니다.
 */
@Getter
@Setter
public class BoardDto {
    private Long id; // 게시글의 고유 식별자

    // ✨ 제안: Bean Validation 어노테이션을 사용하여 입력 값의 유효성을 검사할 수 있습니다.
    @NotBlank(message = "제목은 비워둘 수 없습니다.") // null, "", " " 모두 허용하지 않음
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
    private String title; // 게시글 제목

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    @Size(max = 2000, message = "내용은 2000자를 초과할 수 없습니다.")
    private String contents; // 게시글 내용

    private String writer; // 작성자 이름 (username)

    private String imageUrl; // 게시글에 첨부된 이미지 URL (선택적)

    private LocalDateTime writeDate; // 게시글 작성 또는 마지막 수정 일시
}

/*
 * ✨ 효율성 및 유지보수 제안 ✨
 *
 * 1. DTO 클래스 세분화:
 *    - 현재 `BoardDto` 하나로 게시글 생성, 조회, 수정을 모두 처리하고 있습니다.
 *    - 하지만 각 용도에 따라 필요한 필드가 다를 수 있습니다. 예를 들어,
 *      - **생성(Create)** 시에는 `id`, `writer`, `writeDate`가 필요 없습니다 (서버에서 생성).
 *      - **수정(Update)** 시에는 `id`는 경로(Path)에서 받고, `writer`나 `writeDate`는 변경되지 않아야 합니다.
 *      - **조회(Response)** 시에는 모든 정보가 필요할 수 있습니다.
 *    - `BoardCreateRequestDto`, `BoardUpdateRequestDto`, `BoardResponseDto` 등으로 클래스를 분리하면
 *      각 API의 요구사항을 명확하게 표현할 수 있고, 불필요한 데이터 전송을 막을 수 있으며, 유효성 검사 규칙을 각기 다르게 적용할 수 있어 안정성이 높아집니다.
 *
 * 2. Bean Validation 적용:
 *    - `spring-boot-starter-validation` 의존성을 추가하면, `@NotBlank`, `@Size`, `@NotNull` 등의 어노테이션을 사용하여
 *      DTO 필드의 유효성을 선언적으로 검증할 수 있습니다. (코드에 예시 추가)
 *    - 컨트롤러 메소드에서 해당 DTO를 받는 파라미터에 `@Valid` 어노테이션을 붙이면,
 *      요청이 컨트롤러에 도달하기 전에 자동으로 유효성 검사가 수행됩니다.
 *      `public ResponseEntity<?> createBoard(@Valid @RequestBody BoardDto boardDto) { ... }`
 *
 * 3. 생성자 및 빌더 패턴 사용:
 *    - `@Setter`는 객체의 상태를 언제든지 변경할 수 있게 하여 불변성(immutability)을 해칠 수 있습니다.
 *    - `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder` 같은 Lombok 어노테이션을 사용하여
 *      생성 시점에만 값을 할당하는 불변 객체로 만드는 것을 고려해볼 수 있습니다.
 *    - 특히 `BoardResponseDto`와 같이 조회용으로만 사용되는 DTO는 불변으로 만드는 것이 더 안전합니다.
 *
 * 4. 엔티티 변환 로직 위치:
 *    - DTO와 엔티티 간의 변환 로직은 어디에 위치해야 할까요?
 *      - **DTO 클래스 내부에 `toEntity()` 메소드 정의**: `BoardCreateRequestDto`가 `Board` 엔티티로 변환되는 로직을 포함.
 *      - **엔티티 클래스 내부에 `fromDto()` 정적 메소드 정의**: DTO를 받아 엔티티를 생성.
 *      - **서비스 계층에서 변환 로직 수행**: 서비스 메소드 내에서 직접 변환.
 *      - **별도의 Mapper 클래스/인터페이스 사용 (e.g., MapStruct)**: 변환 로직을 완전히 분리.
 *    - 프로젝트의 규모와 복잡성에 따라 적절한 방식을 선택할 수 있습니다. 작은 프로젝트에서는 DTO나 서비스 계층에 두는 것이 간편하고,
 *      큰 프로젝트에서는 MapStruct와 같은 라이브러리를 사용하여 보일러플레이트 코드를 줄이는 것이 효율적입니다.
 */
