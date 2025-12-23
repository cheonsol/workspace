package com.kh.textGame.controller;

import com.kh.textGame.dto.BattleResultDto;
import com.kh.textGame.service.BattleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/battle")
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;

    @PostMapping("/attack")
    public ResponseEntity<BattleResultDto> attack(@RequestBody BattleResultDto request) {
        BattleResultDto result = battleService.attack(request.getMonsterId(), request.getMemberId());
        return ResponseEntity.ok(result);
    }
}