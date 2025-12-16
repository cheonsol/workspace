package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // 댓글 내용

    private String writer;  // 댓글 작성자 (필요 시 추가)

    @CreationTimestamp // DB에 저장될 때 시간 자동 기록
    private LocalDateTime writeDate;

    // == 연관 관계 (N:1) ==
    // 댓글 여러 개가 하나의 게시글에 달림
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // DB 컬럼명: board_id
    private Board board;

    @Builder
    public Comment(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        this.board = board;
    }
}