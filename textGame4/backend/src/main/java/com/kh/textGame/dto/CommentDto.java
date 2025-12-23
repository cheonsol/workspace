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

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    private String content;

    private String writer;

    private LocalDateTime writeDate;

    private Long boardId;
}