package com.kh.archive.service;

import com.kh.archive.Repasitory.MemberRepository;
import com.kh.archive.dto.MemberDTO;
import com.kh.archive.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void register(MemberDTO memberDTO) {
        if (memberRepository.existsByUserId(memberDTO.getUserId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        if (memberDTO.getPassword().length() < 4) {
            throw new IllegalArgumentException("비밀번호는 4자 이상이어야 합니다.");
        }

        Member member = new Member();
        member.setUserId(memberDTO.getUserId());
        member.setPassword(memberDTO.getPassword());
        member.setNickname(memberDTO.getNickname());
        member.setEmail(memberDTO.getEmail());

        memberRepository.save(member);
    }

    @Override
    public Member login(String userId, String password) {
        return memberRepository.findByUserId(userId)
                .filter(m -> m.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}