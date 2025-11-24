package com.kh.spring.controller;

import com.kh.spring.model.vo.Board;
import com.kh.spring.model.vo.Category;
import com.kh.spring.model.vo.Member;
import com.kh.spring.model.vo.Reply;
import com.kh.spring.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.server.Dispatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시글 목록 조회
    @GetMapping("list.bo")
    public String selectBoardList(@RequestParam(value = "cpage", defaultValue = "1") int cuurentPage, Model model) {
        Map<String, Object> result = boardService.getBoardList(cuurentPage);

        model.addAttribute("list", result.get("list"));
        model.addAttribute("pi",  result.get("pi"));

        return "board/listView";
    }

    @GetMapping("/enrollForm.bo")
    public String enrollForm(Model model) {
        List<Category> categories = boardService.getCategories();

        model.addAttribute("categories", categories);

        return "board/enrollForm";
    }

    //spring boot에는 spring-boot-starter-web의존을 추가하면
    @PostMapping("/insert.bo")
    public String insertBoard(Board board,
                              @RequestParam(value = "upfile", required = false) MultipartFile upfile,
                              HttpSession session, Model model) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        board.setBoardWriter(loginMember.getMemberNo());

        int result = boardService.insertBoard(board, upfile);

        if(result > 0){
            session.setAttribute("alertMsg", "게시글이 등록되었습니다.");
            return "redirect:/list.bo";
        } else {
            model.addAttribute("errorMsg", "게시글 등록에 실패하였습니다.");
            return "common/error";
        }
    }

    @GetMapping("/detail.bo")
    public String detailBoard(@RequestParam(value = "bno", required = true) int boardNo, Model model) {
        Map<String,Object> result = boardService.getBoardByIdWithCount(boardNo);

        if(result.get("board") != null){
            model.addAttribute("board", result.get("board"));
            model.addAttribute("at", result.get("attachment"));

            return "board/detailView";
        } else {
            model.addAttribute("errorMsg", "게시글 조회 실패");
            return "common/error";
        }
    }

    //게시글 수정
    @GetMapping("/updateForm.bo")
    public String updateForm(@RequestParam("bno") int boardNo, Model model) {
        List<Category> categories = boardService.getCategories();
        Map<String, Object> result = boardService.getBoardById(boardNo);

        model.addAttribute("categories", categories);
        model.addAttribute("board", result.get("board"));
        model.addAttribute("at", result.get("attachment"));

        return "board/updateForm";
    }

    //게시글 수정
    @PostMapping("/update.bo")
    public String updateBoard(Board board,
                              @RequestParam(value = "originFileNo", required = false) Integer originFileNo,
                              @RequestParam(value = "upfile", required = false) MultipartFile upfile,
                              HttpSession session, Model model) {
        //전달받은 내용을 토대로 수정, 결과페이지로 리턴
        int result = boardService.updateBoard(board, upfile, originFileNo);

        if(result > 0){
            session.setAttribute("alertMsg", "게시글이 수정되었습니다.");
            return "redirect:/detail.bo?bno=" + board.getBoardNo();
        } else {
            model.addAttribute("errorMsg", "게시글 수정 실패");
            return "common/error";
        }
    }

    //댓글 작성(AJAX)
    @PostMapping("/rinsert.bo")
    @ResponseBody
    public String insertReply(@RequestParam("boardNo") int boardNo,
                              @RequestParam("content") String content,
                              HttpSession session) {
        Member m = (Member)session.getAttribute("loginMember");

        Reply reply = new Reply();
        reply.setRefBoardNo(boardNo);
        reply.setReplyContent(content);
        reply.setReplyWriter(m.getMemberNo());

        int result = boardService.insertReply(reply);

        return result > 0 ? "1" : "0";
    }

    /*
        spring-boot-starter-web
        -> jackson라이브러리를 기본적으로 탑제하고 있기 때문에 응답시 객체를 응답한다면 json문자로 변환해준다.

        @ResponseBody -> 응답을 응답바디로 직접 보내도록 지정
        jackson -> json 문자열로 변환 -> 응답바디에 전달
     */
    //댓글목록
    @GetMapping("/rlist.bo")
    @ResponseBody
    public List<Reply> selectReply(@RequestParam("boardNo") int boardNo) {
       return boardService.getReplyListByBoardNo(boardNo);
    }

    @GetMapping("rdelete.bo")
    @ResponseBody
    public String deleteReply(@RequestParam("replyNo") int replyNo) {
        int result = boardService.removeReply(replyNo);

        return result > 0 ? "1" : "0";
    }
}
