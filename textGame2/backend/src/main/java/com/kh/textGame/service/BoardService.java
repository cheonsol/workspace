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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 게시글 생성
    public BoardDto createBoard(BoardDto boardDto, String username) {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .writer(member.getNickname()) // writer 필드에 닉네임 저장
                .imageUrl(boardDto.getImageUrl())
                .build();
        Board savedBoard = boardRepository.save(board);
        return convertEntityToDto(savedBoard);
    }

    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // 게시글 ID로 조회
    @Transactional(readOnly = true)
    public BoardDto getBoardById(Long id) {
        return boardRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }
    
    // 게시글 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto, String username) throws IllegalAccessException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!board.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("User does not have permission to update this board.");
        }
        
        board.setTitle(boardDto.getTitle());
        board.setContents(boardDto.getContents());
        board.setImageUrl(boardDto.getImageUrl());
        Board updatedBoard = boardRepository.save(board);
        return convertEntityToDto(updatedBoard);
    }

    // 게시글 삭제
    public void deleteBoard(Long id, String username) throws IllegalAccessException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid board Id:" + id));
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!board.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("User does not have permission to delete this board.");
        }
        boardRepository.deleteById(id);
    }
    
    private BoardDto convertEntityToDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContents(board.getContents());
        boardDto.setWriter(board.getWriter());
        boardDto.setImageUrl(board.getImageUrl());
        boardDto.setWriteDate(board.getWriteDate());
        return boardDto;
    }
}
