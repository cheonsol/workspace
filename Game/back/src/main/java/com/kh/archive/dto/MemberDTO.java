package com.kh.archive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private String id;
    private String email;
    private String password;
    private String nickname;
    private String regDate;
}
