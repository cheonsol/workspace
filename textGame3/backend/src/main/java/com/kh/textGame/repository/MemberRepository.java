package com.kh.textGame.repository;

import com.kh.textGame.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 로그인할 때 아이디로 회원을 찾아야 하므로 추가
    Optional<Member> findByUserId(String userId);

    // 닉네임 중복 체크용
    boolean existsByNickname(String nickname);

    // 아이디 중복 체크용
    boolean existsByUserId(String userId);
}