package com.kh.archive.service;

import com.kh.archive.dto.MemberDTO;
import com.kh.archive.entity.Member;

public interface MemberService {

    void register(MemberDTO memberDTO) throws Exception;

    Member login(String userId, String password);
}
