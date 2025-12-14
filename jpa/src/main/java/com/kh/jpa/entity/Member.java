package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicInsert
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @Column(name = "USER_PWD", nullable = false, length = 100)
    private String userPwd;

    @Column(name = "USER_NAME", nullable = false, length = 15)
    private String userName;

    @Column(length = 254)
    private String email;

    @Column(columnDefinition = "char(1)")
    private String gender;

    private Integer age;

    @Column(length = 13)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(name = "ENROLL_DATE")
    @CreationTimestamp
    private LocalDateTime enrollDate;

    @Column(name = "MODIFY_DATE")
    @CreationTimestamp
    private LocalDateTime modifyDate;

    @Column(columnDefinition = "char(1) default 'Y'")
    private String status;
}