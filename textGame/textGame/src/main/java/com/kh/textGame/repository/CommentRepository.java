package com.kh.textGame.repository;

import com.kh.textGame.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 기본 기능만으로도 충분함
}