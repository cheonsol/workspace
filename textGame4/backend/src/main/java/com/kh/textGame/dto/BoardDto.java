package com.kh.textGame.dto;

import com.kh.textGame.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BoardDto {
    private Long id;

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    @Size(max = 100, message = "제목은 100자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    @Size(max = 2000, message = "내용은 2000자를 초과할 수 없습니다.")
    private String contents;

    private String writer;

    private String imageUrl;

    private LocalDateTime writeDate;

    private Boolean isShow;

    public static BoardDto from(Board entity) {
        return BoardDto.builder()
                .title(entity.getTitle())
                .contents(entity.getContents())
                // 작성자(Member)의 닉네임을 가져오는 로직 등
                .writer(entity.getWriter() != null ? entity.getWriter().getNickname() : "익명")
                .imageUrl(entity.getImageUrl())
                .writeDate(entity.getWriteDate())
                .isShow(entity.isShow())
                .build();
    }

}