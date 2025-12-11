package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.swing.*;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NOTICE")
public class Notice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNO;

    @Column(name = "NOTICE_TITLE", nullable = false)
    private String noticeTitle;

    @ManyToOne
    @JoinColumn(name = "NOTICE_WRITER", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member noticeWriter;

    @Column(name = "NOTICE_CONTENT", nullable = false)
    private String noticeContent;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false)
    private LocalDate createDate;
}
