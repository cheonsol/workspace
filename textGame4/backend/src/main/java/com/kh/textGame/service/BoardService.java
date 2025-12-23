package com.kh.textGame.service;

import com.kh.textGame.dto.BoardDto;
import com.kh.textGame.entity.Board;
import com.kh.textGame.entity.Member;
import com.kh.textGame.repository.BoardRepository;
import com.kh.textGame.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public BoardDto createBoard(BoardDto boardDto, Long userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .writer(member.getNickname())
                .imageUrl(boardDto.getImageUrl())
                .build();
        Board savedBoard = boardRepository.save(board);
        return convertEntityToDto(savedBoard);
    }


    @Transactional(readOnly = true)
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }


    @Transactional(readOnly = true)
    public BoardDto getBoardById(Long id) {
        return boardRepository.findById(id)
                .map(Board::from)
                .orElse(null);
    }
    

    public BoardDto updateBoard(Long id, BoardDto boardDto, Long userId) throws IllegalAccessException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!board.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("작성자가 아닙니다.");
        }
        
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setImageUrl(boardDto.getImageUrl());
        Board updatedBoard = boardRepository.save(board);
        return convertEntityToDto(updatedBoard);
    }

    @Transactional
    public BoardDto deleteBoard(Long id, Long userId) throws IllegalAccessException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!board.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("작성자가 아닙니다.");
        }

        board.setShow(false);
        return BoardDto.from(board);
    }
}
