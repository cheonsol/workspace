package com.example.jpa.entity;

import com.example.jpa.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REPLY")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private Long replyNo;

    @Column(name = "REPLY_CONTENT", length = 400, nullable = false)
    private String replyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "REF_BNO")
    private Board refBno;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "REPLY_WRITER")
    private Member replyWriter;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @Column(name = "STATUS", length = 1, nullable = false)
    @Enumerated(EnumType.STRING)
    private Enums.UseStatus status;
}
