package com.kh.board.controller.dto.request;

import com.kh.board.entity.Board;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter
    @Setter

    // 생성을 위한 Dto를 만든다.
    public static class CreateDto{
        private String title;
        private String user_id;
        private String contents;
        private String file_name;

        // buider를 Entity에 넣는다.
        // Entity가 뭐시여?
        // Dto로 사용하기 위해 바꾸는 요소?
        public Board toEntity(){
            return Board.builder()
                    .title(title)
                    .memberEmail(user_id)
                    .contents(contents)
                    .fileName(file_name)
                    .build();
        }
    }
}
