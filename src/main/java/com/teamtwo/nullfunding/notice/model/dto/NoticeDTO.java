package com.teamtwo.nullfunding.notice.model.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class NoticeDTO {

    private int noticeNo; // 공지 번호(시퀀스)
    private int memberCode; // 회원번호(시퀀스 참조)
    private String nickName; // 회원닉네임
    private String noticeTitle; // 공지 제목
    private String noticeContent; // 공지 내용
    private Date uploadDate; // 공지 작성일
    private int readCount; // 조회수
    private List<NoticeMediaDTO> MediaList; // 첨부파일

}
