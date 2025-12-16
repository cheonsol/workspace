package com.kh.textGame.controller;

import com.kh.textGame.dto.CommentDto;
import com.kh.textGame.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // /api/boards/{boardId}/comments, /api/comments/{commentId}
public class CommentController {

    private final CommentService commentService;

    // 특정 게시글의 모든 댓글 조회
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentsByBoardId(boardId));
    }

    // 댓글 생성
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long boardId, @RequestBody CommentDto commentDto, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        commentDto.setBoardId(boardId); // PathVariable에서 받은 boardId를 DTO에 설정
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentDto, username));
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(commentService.updateComment(commentId, commentDto, username));
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        try {
            commentService.deleteComment(commentId, username);
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
