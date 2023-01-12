package com.teamtwo.nullfunding.home.dto;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class IndexCommunityDTO {

    private int articleNo; // 게시글 번호
    private int memCode; // 회원번호(시퀀스), 닉네임?
    private String nickName; // 회원닉네임
    private String title; // 제목
    private String content; // 내용
    private Date uploadDate; // 작성일
    private int readCount; // 조회수
    private MemberDTO writer;
}
