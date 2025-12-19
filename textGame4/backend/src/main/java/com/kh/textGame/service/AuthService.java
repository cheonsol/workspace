package com.kh.textGame.service;

import com.kh.textGame.dto.LoginDto;
import com.kh.textGame.dto.SignUpDto;
import com.kh.textGame.entity.Member;
import com.kh.textGame.jwt.JwtUtil;
import com.kh.textGame.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Member signup(SignUpDto signUpDto) {
        if (memberRepository.existsByUserId(signUpDto.getUserId())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        if (memberRepository.existsByNickname(signUpDto.getNickname())) {
            throw new RuntimeException("Error: Nickname is already taken!");
        }

        Member member = Member.builder()
                .userId(signUpDto.getUserId())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();

        return memberRepository.save(member);
    }

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword()));

        // If authentication is successful, generate a token
        return jwtUtil.generateToken(loginDto.getUserId());
    }
}
