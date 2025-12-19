package com.kh.textGame.controller;

import com.kh.textGame.dto.BattleTurnResultDto;
import com.kh.textGame.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/attack")
    public ResponseEntity<BattleTurnResultDto> handleAttack(@RequestBody Map<String, Long> request) {
        Long monsterId = request.get("monsterId");
        // Assume a fixed user ID for testing
        Long memberId = 1L; // Replace with actual logged in user logic
        BattleTurnResultDto result = gameService.handleAttack(memberId, monsterId);
        return ResponseEntity.ok(result);
    }
}
