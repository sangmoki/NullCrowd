package com.teamtwo.nullfunding.community.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ComcommentDTO {
    
    private int commentNo; // 댓글번호(시퀀스)
    private int articleNo; // 게시글번호(시퀀스)
    private int memCode; // 회원번호(시퀀스)
    private String content; // 내용
    private Date uploadDate; // 작성일
}
