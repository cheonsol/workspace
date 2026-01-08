package com.kh.archive.service;


import com.kh.archive.entity.Ranking;
import java.util.List;

public interface RankingService {
    List<Ranking> getTop10Rankings();
    int getGuestBestScore(String guestId);
    void saveRanking(Ranking ranking);
}
