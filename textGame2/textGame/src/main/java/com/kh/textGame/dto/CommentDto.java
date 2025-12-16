package com.kh.textGame.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String writer;
    private LocalDateTime writeDate;
    private Long boardId; // 댓글이 속한 게시글 ID
}
