package com.kh.textGame.repository;

import com.kh.textGame.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByIsShowTrueOrderByIdDesc();

    Optional<Board> findByIdAndIsShowTrue(Long id);
}