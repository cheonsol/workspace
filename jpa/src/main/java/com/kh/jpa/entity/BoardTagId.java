package com.kh.jpa.entity;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardTagId implements Serializable {
    private Long board;
    private Long tag;
}