package com.kh.board.controller.dto.response;

import com.kh.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class BoardResponse {

    @Getter
    @Setter
    @Builder

    // Dto : Data Transfer Object => 데이터를 이동하기 위한 객체
    // Vo를 사용하지 않는 이유 : Vo는 DB의 정보를 대조하여 가져오는 것.
    // Rest는 DB와 직접 연결되기 때문에 Dto를 통해 연결하는 것이 좋다.
    public static class SimpleDto{
        private String board_id;
        private String member_email;
        private String title;
        private LocalDateTime created_at;

        // Builder : 원하는 값만 생성하기 위하여 사용.
        // 원리 : board라는 객체를 만들고 다시 Build 객체에 값을 this를 통해 넣는다.
        // build로 return하기 때문에 원하는 값만 넣을 수 있다.
        public static SimpleDto of(Board board){
            return SimpleDto.builder()
                    .board_id(board.getBoardId())
                    .member_email(board.getMemberEmail())
                    .title(board.getTitle())
                    .created_at(board.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class DetailDto{
        private String board_id;
        private String member_email;
        private String title;
        private String contents;
        private String file_name;
        private LocalDateTime created_at;

        public static DetailDto of(Board board){
            return DetailDto.builder()
                    .board_id(board.getBoardId())
                    .member_email(board.getMemberEmail())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .file_name(board.getFileName())
                    .created_at(board.getCreatedAt())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class updateDto{
        private String board_id;
        private String member_email;
        private String title;
        private String contents;
        private String file_name;
        private LocalDateTime created_at;

        public static updateDto of(Board board){
            return updateDto.builder()
                    .board_id(board.getBoardId())
                    .member_email(board.getMemberEmail())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .file_name(board.getFileName())
                    .created_at(board.getCreatedAt())
                    .build();
        }
    }
}
