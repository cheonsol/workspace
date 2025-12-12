package com.example.jpa.entity;

import java.io.Serializable;
import java.util.Objects;

public class BoardTagId implements Serializable {

    private Long board;
    private Long tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardTagId that = (BoardTagId) o;
        return Objects.equals(board, that.board) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, tag);
    }
}
