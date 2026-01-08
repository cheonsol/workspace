package com.kh.archive.service;

import com.kh.archive.entity.Ranking;
import com.kh.archive.repository.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ranking> getTop10Rankings() {
        return rankingRepository.findTop10ByOrderByScoreDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public int getGuestBestScore(String guestId) {
        return rankingRepository.findFirstByGuestIdOrderByScoreDesc(guestId)
                .map(Ranking::getScore)
                .orElse(0);
    }

    @Override
    @Transactional
    public void saveRanking(Ranking ranking) {
        // 단순히 저장하거나, 혹은 특정 로직(예: 비속어 필터링)을 여기에 추가할 수 있습니다.
        rankingRepository.save(ranking);
    }
}