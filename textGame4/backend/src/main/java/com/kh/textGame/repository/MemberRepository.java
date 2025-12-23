package com.kh.textGame.repository;

import com.kh.textGame.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserId(String userId);

    boolean existsByNickname(String Nickname);

    Optional<Member> findByUserId(Long userId);
}