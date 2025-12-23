package com.kh.textGame.controller;

import com.kh.textGame.dto.BoardDto;
import com.kh.textGame.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardDto boardDto, Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(boardService.createBoard(boardDto, username));
    }

    @GetMapping
    public ResponseEntity<List<BoardDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Long id) {
        BoardDto boardDto = boardService.getBoardById(id);
        if (boardDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boardDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BoardDto> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto, Authentication authentication) {
        String username = authentication.getName();
        try {
            return ResponseEntity.ok(boardService.updateBoard(id, boardDto, username));
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        try {
            boardService.deleteBoard(id, username);
            return ResponseEntity.ok().build();
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}