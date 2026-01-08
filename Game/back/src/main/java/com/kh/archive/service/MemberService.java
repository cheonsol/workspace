package com.kh.archive.service;

import com.kh.archive.entity.Member;

public interface MemberService {
    Member findByEmail(String email);
    void updateScore(String email, int score);
}