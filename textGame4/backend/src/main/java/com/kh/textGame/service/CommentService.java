package com.kh.textGame.service;

import com.kh.textGame.dto.CommentDto;
import com.kh.textGame.entity.Board;
import com.kh.textGame.entity.Comment;
import com.kh.textGame.entity.Member;
import com.kh.textGame.repository.BoardRepository;
import com.kh.textGame.repository.CommentRepository;
import com.kh.textGame.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public CommentDto createComment(CommentDto commentDto, String username) {
        Board board = boardRepository.findById(commentDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID: " + commentDto.getBoardId()));
        
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .writer(member.getNickname())
                .board(board)
                .build();
        Comment savedComment = commentRepository.save(comment);
        return convertEntityToDto(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByBoardId(Long boardId) {
        return commentRepository.findAllByBoardId(boardId).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    public CommentDto updateComment(Long commentId, CommentDto commentDto, String username) throws IllegalAccessException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + commentId));
        
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!comment.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("User does not have permission to update this comment.");
        }
        
        comment.setContent(commentDto.getContent()); // Use the setter
        Comment updatedComment = commentRepository.save(comment);
        return convertEntityToDto(updatedComment);
    }

    public void deleteComment(Long commentId, String username) throws IllegalAccessException {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + commentId));
        
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user"));

        if (!comment.getWriter().equals(member.getNickname())) {
            throw new IllegalAccessException("User does not have permission to delete this comment.");
        }
        commentRepository.deleteById(commentId);
    }
    
    private CommentDto convertEntityToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setWriter(comment.getWriter());
        commentDto.setWriteDate(comment.getWriteDate());
        commentDto.setBoardId(comment.getBoard().getId());
        return commentDto;
    }
}
