package com.teamtwo.nullfunding.community.model.dto;

<<<<<<< HEAD
import com.teamtwo.nullfunding.member.dto.MemberDTO;
=======
>>>>>>> origin/master
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CommunityDTO {

    private int commentNo; // 게시글 번호
    private int memCode; // 회원번호(시퀀스), 닉네임?
    private String title; // 제목
    private String content; // 내용
    private Date uploadDate; // 작성일
<<<<<<< HEAD
    private MemberDTO writer;
=======
>>>>>>> origin/master
}
