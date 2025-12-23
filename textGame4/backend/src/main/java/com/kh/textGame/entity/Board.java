package com.kh.textGame.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member writer;


    private String imageUrl;

    @Column(nullable = false)
    private boolean isShow = true;

    @CreatedDate
    private LocalDateTime writeDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Builder
    public Board(String title, String contents, Member writer, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.imageUrl = imageUrl;
    }

    public void update(String title, String contents, String imageUrl) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
    }

    public void delete() {
        this.isShow = false;
    }
}