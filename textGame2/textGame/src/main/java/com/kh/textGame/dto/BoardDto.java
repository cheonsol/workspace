package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String imageUrl;
    private LocalDateTime writeDate;
}
