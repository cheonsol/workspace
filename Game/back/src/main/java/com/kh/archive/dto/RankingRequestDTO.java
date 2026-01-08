package com.kh.archive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingRequestDTO {
    private String nickname;
    private int score;
    private String guestId;
}
