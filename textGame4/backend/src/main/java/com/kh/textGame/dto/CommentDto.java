package com.kh.textGame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;

    @NotBlank(message = "댓글 내용은 비워둘 수 없습니다.")
    @Size(max = 500, message = "댓글은 500자를 초과할 수 없습니다.")
    private String content;

    private String writer;

    private LocalDateTime writeDate;

    @NotNull(message = "게시글 ID는 필수입니다.")
    private Long boardId;
}