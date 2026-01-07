package com.kh.archive.security;

import com.kh.archive.entity.Member;
import com.kh.archive.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // DB에서 이메일로 사용자 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + email));

        // 스프링 시큐리티가 이해할 수 있는 UserDetails 객체로 변환하여 반환
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword()) // 현재는 평문 암호 사용 중
                .roles("USER") // 권한 설정
                .build();
    }
}