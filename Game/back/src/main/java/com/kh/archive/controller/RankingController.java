package com.kh.archive.controller;

import com.kh.archive.entity.Ranking;
import com.kh.archive.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingRepository rankingRepository;

    @GetMapping
    public List<Ranking> getTop10() {
        return rankingRepository.findTop10ByOrderByScoreDesc();
    }

    @PostMapping
    public void saveScore(@RequestBody Ranking ranking) {
        rankingRepository.save(ranking);
    }
}
