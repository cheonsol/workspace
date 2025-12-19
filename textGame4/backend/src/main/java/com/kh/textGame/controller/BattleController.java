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
        // 리액트에서 보낸 플레이어 ID와 몬스터 ID를 받아 서비스를 실행합니다.
        BattleResultDto result = battleService.attack(request.getMonsterId(), request.getMemberId());
        return ResponseEntity.ok(result);
    }
}