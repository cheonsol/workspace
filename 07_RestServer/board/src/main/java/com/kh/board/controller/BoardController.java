package com.kh.board.controller;

import com.kh.board.controller.dto.request.BoardRequest;
import com.kh.board.controller.dto.response.BoardResponse;
import com.kh.board.entity.Board;
import com.kh.board.entity.Member;
import com.kh.board.mapper.BoardMapper;
import com.kh.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 이름으로 추측하길 생성자 만들어주는 거?
@RequiredArgsConstructor
// RestApi로 하는데, 자동으로 전부 Body로 데이터를 반환한다.
// 왜?
// 원래는 뭘로 반환하는데?
// 주소아닌가?
@RestController//모든 controller 메서드의 리턴을 ResponseBody로 처리하여 데이터를 반환한다.
@RequestMapping("/api/board")
public class BoardController {

    // 불변을 위해 final로 선언
    // 왜 불변?
    // service는 변하면 안되니까
    // 왜? 왜에에에에엥?
    private final BoardService boardService;

    //@ResponseBody

    // GetMapping url을 받아온다.
    // 이때 받아오는 주소는 @RequestMapping("/api/board")이다.
    @GetMapping
    // ResponseEntity 타입으로 getBoards를 만든다.
    public ResponseEntity<List<BoardResponse.SimpleDto>> getBoards(){
        //게시글 목록을 데이터베이스로부터 가져와 반환
        // findAll 메서드를 이용함.
        List<Board> boards = boardService.findAll();

        // Dto의 정보를 새로운 배열로 만들어 result에 넣는다.
        List<BoardResponse.SimpleDto> result = new ArrayList<>();
        // 반복문을 통해 result를 board에 넣는다.
        // of는 뭐고?
        for (Board board : boards){
            result.add(BoardResponse.SimpleDto.of(board));
        }
        // 새로운 Entity 객체로 만들어 반환. 성고했을 때, http ok를 띄운다.
        // 따로 입력해주는 이유?
        //  구체적으로 구분이 되어있지 않기 때문이다.
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    // createBoard이름으로 CreateDto에서 request를 받아온다.
    // File의 경우 MultipartFile로 받아온다.
    public ResponseEntity<String> createBoard(BoardRequest.CreateDto request, MultipartFile upfile) throws IOException {
       // 무슨 exception인지 구분을 위해 request가 없을 떄나, user_id가 없을 때, bad_request를 띄운다.
        if (request == null || request.getUser_id() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!upfile.isEmpty()){
            File file = new File("C:\\workspaceJ\\07_RestServer\\board\\src\\main\\resources\\uploads", upfile.getOriginalFilename());
            upfile.transferTo(file);

            request.setFile_name("/uploads/"+upfile.getOriginalFilename());
        }

        Board board = request.toEntity();
        int result = boardService.save(board);

        if(result > 0){
            return new ResponseEntity<>("게시글 등록 성공", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("게시글 등록 실패", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse.DetailDto> getBoard(@PathVariable Long boardId){
        Board board = boardService.findOne(boardId);
        BoardResponse.DetailDto result = BoardResponse.DetailDto.of(board);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId) {
        int result = boardService.delete(boardId);
        return new ResponseEntity<>(result + "개의 게시글 삭제 완료", HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<String> updateBoard(@PathVariable Long boardId) {
        int result = boardService.update(boardId);
        return new ResponseEntity<>(result + "개의 게시글 수정 완료", HttpStatus.OK);
    }
}
