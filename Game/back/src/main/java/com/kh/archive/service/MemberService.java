package com.kh.archive.service;

import com.kh.archive.dto.MemberDTO;
import com.kh.archive.entity.Member;

public interface MemberService {
    void register(MemberDTO memberDTO);
    Member login(String email, String password);
    Member findByEmail(String email);
}