package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
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
}