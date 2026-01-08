package com.kh.archive.repository;

import com.kh.archive.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
    Optional<Ranking> findFirstByGuestIdOrderByScoreDesc(String guestId);

    List<Ranking> findTop10ByOrderByScoreDesc();
}