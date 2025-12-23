package com.kh.textGame.repository;

import com.kh.textGame.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserId(String userId);

    boolean existsByNickname(String Nickname);

    @Query("SELECT m FROM Member m " +
            "LEFT JOIN FETCH m.memberSkills ms " +
            "LEFT JOIN FETCH ms.skill " +
            "WHERE m.userId = :userId")
    Optional<Member> findByUserIdWithSkills(@Param("userId") String userId);

    Optional<Member> findByUserId(String userId);
}