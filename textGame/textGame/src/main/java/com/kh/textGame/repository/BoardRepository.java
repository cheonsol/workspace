package com.kh.textGame.repository;

import com.kh.textGame.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // isShow가 true인 (삭제되지 않은) 게시글만 최신순(내림차순)으로 조회
    // SQL: SELECT * FROM board WHERE is_show = true ORDER BY id DESC
    List<Board> findAllByIsShowTrueOrderByIdDesc();
}