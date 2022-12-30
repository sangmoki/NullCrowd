package com.teamtwo.nullfunding.notice.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class NoticeMediaDTO {
    
    private int mediaNo; // 영상번호(시퀀스)
    private int noticeNo; // 공지사항 번호
    private String fileName; // 파일 이름
    private String pathName; // 저장 이름
    private String url; // url 주소
    private char status; // 삭제 여부
    private char mediaType; // 영상 타입(I/V) 이미지/비디오
            
}
