package com.kh.archive.dto;

import lombok.Data;

@Data
public class MemberDTO {
    private String userId;
    private String password;
    private String nickname;
    private String email;
}
