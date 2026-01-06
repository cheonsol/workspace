package com.kh.archive.controller;
import com.kh.archive.dto.LoginDTO;
import com.kh.archive.dto.MemberDTO;
import com.kh.archive.entity.Member;
import com.kh.archive.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // 리액트 접속 허용
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.register(memberDTO);
            return ResponseEntity.ok("새로운 기록관이 등록되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 실패");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Member loginMember = memberService.login(loginDTO.getUserId(), loginDTO.getPassword());
            // 성공 시 닉네임 등을 반환하여 프론트에서 활용하게 함
            return ResponseEntity.ok(loginMember.getNickname() + " 기록관님, 환영합니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}