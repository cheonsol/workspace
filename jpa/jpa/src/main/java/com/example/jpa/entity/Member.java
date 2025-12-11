package com.example.jpa.entity;

import com.example.jpa.enums.Enums;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "USER_ID", length = 30)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "USER_PWD", length = 100, nullable = false)
    private String userPwd;

    @Column(name = "USER_NAME", length = 15, nullable = false)
    private String userName;

    @Column(name = "EMAIL", length = 254)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 1) // M or F
    private Enums.MemberGender gender;

    @Column(name = "AGE")
    private int age;

    @Column(name = "PHONE", length = 13)
    private String phone;

    @Column(name = "ADDRESS", length = 100)
    private String address;

    @CreationTimestamp
    @Column(name = "ENROLL_DATE", updatable = false)
    private LocalDate enrollDate;

    @UpdateTimestamp
    @Column(name = "MODIFY_DATE")
    private LocalDate modifyDate;

    @ColumnDefault("'Y'")
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 1, nullable = false)// y랑 n 중에 하나
    private Enums.UseStatus status;

}
