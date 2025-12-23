package com.kh.textGame.controller;

import com.kh.textGame.dto.LoginDto;
import com.kh.textGame.dto.SignUpDto;
import com.kh.textGame.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        try {
            authService.signup(signUpDto);
            return ResponseEntity.ok("회원가입에 성공했습니다!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        try {
            authService.login(loginDto);
            return ResponseEntity.ok("환영합니다.");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("아이디 혹은 비밀번호가 옳지 않습니다.");
        }
    }
}