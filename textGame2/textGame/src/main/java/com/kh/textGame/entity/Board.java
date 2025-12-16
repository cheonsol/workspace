package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter // 간단한 수정을 위해 추가 (실무에선 update 메서드 별도 생성 권장)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // 긴 글 저장을 위해
    private String contents; // 프론트의 contents와 매칭

    @Column(nullable = false)
    private String writer;

    private String imageUrl;

    // show : false 기능을 위한 필드 (true면 보임, false면 삭제됨)
    @Column(nullable = false)
    private boolean isShow = true;

    @CreationTimestamp // 작성일 자동 저장
    private LocalDateTime writeDate;

    @UpdateTimestamp // 수정일 자동 저장 (선택 사항)
    private LocalDateTime updateDate;

    // == 연관 관계 (1:N) ==
    // 게시글 하나에 댓글 여러 개
    // mappedBy: Comment 클래스의 board 변수명
    // CascadeType.ALL: 게시글 지우면 댓글도 다 같이 삭제됨
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 댓글 추가 편의 메서드
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Builder
    public Board(String title, String contents, String writer, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.imageUrl = imageUrl;
        this.isShow = true; // 기본값: 보임
    }
}