package com.kh.archive.controller;
import com.kh.archive.dto.LoginDTO;
import com.kh.archive.dto.MemberDTO;
import com.kh.archive.entity.Member;
import com.kh.archive.security.JwtUtil;
import com.kh.archive.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        try {
            memberService.register(memberDTO);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 실패");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Member loginMember = memberService.login(loginDTO.getEmail(), loginDTO.getPassword());

            String token = jwtUtil.createToken(loginMember.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("nickname", loginMember.getNickname());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("로그인 정보가 올바르지 않습니다.");
        }
    }


    @GetMapping("member/me")
    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal UserDetails userDetails) {
        // 1. JwtFilter에서 저장한 유저 정보(이메일)를 가져옵니다.
        if (userDetails == null) {
            return ResponseEntity.status(401).body("인증 정보가 없습니다.");
        }

        String email = userDetails.getUsername();

        // 2. 이메일을 사용해 DB에서 최신 회원 정보를 조회합니다.
        Member member = memberService.findByEmail(email);

        // 3. 필요한 정보(닉네임, 이메일 등)를 반환합니다.
        return ResponseEntity.ok(Map.of(
                "email", member.getEmail(),
                "nickname", member.getNickname()
        ));
    }
}