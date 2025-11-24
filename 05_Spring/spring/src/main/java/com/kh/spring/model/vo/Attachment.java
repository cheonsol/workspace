package com.kh.spring.model.vo;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    private int fileNo;
    private int refBoardNo;
    private String originName;
    private String changeName;
    private String filePath;
    private Date uploadDate;
    private int fileLevel;
    private String status;
}
