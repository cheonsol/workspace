package com.kh.archive.controller;

import com.kh.archive.dto.RankingRequestDTO;
import com.kh.archive.entity.Ranking;
import com.kh.archive.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService; // Repository 대신 Service 주입

    @GetMapping
    public List<Ranking> getTop10() {
        return rankingService.getTop10Rankings();
    }

    @GetMapping("/my-best/{guestId}")
    public ResponseEntity<Integer> getMyBest(@PathVariable String guestId) {
        int bestScore = rankingService.getGuestBestScore(guestId);
        return ResponseEntity.ok(bestScore);
    }

    @PostMapping
    public ResponseEntity<String> saveScore(@RequestBody RankingRequestDTO dto) {
        Ranking ranking = Ranking.builder()
                .nickname(dto.getNickname())
                .score(dto.getScore())
                .guestId(dto.getGuestId())
                .build();

        rankingService.saveRanking(ranking);
        return ResponseEntity.ok("점수 등록 완료!");
    }
}