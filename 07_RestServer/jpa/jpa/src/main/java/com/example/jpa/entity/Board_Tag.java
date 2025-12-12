package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOARD_TAG")
public class Board_Tag {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_NO")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tag tag;
}
