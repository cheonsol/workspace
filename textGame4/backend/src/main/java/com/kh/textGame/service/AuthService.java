package com.kh.textGame.service;

import com.kh.textGame.dto.LoginDto;
import com.kh.textGame.dto.SignUpDto;
import com.kh.textGame.entity.Member;
import com.kh.textGame.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member signup(SignUpDto signUpDto) {
        if (memberRepository.existsByUserId(signUpDto.getUserId())) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        if (memberRepository.existsByNickname(signUpDto.getNickname())) {
            throw new RuntimeException("이미 존재하는 비밀번호입니다.");
        }

        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();

        return memberRepository.save(member);
    }

    public boolean login(LoginDto loginDto) {
            Member member = memberRepository.findByUserId(loginDto.getUserId())
                    .orElseThrow(() -> new UsernameNotFoundException("아이디를 찾을 수 없습니다. " + loginDto.getUserId()));
            return member.getPassword().equals(loginDto.getPassword());
    }
}
