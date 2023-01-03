package com.teamtwo.nullfunding.community.model.dto;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
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
    private String nickName; // 회원닉네임
    private String content; // 내용
    private Date uploadDate; // 작성일
    private MemberDTO writer;
}
