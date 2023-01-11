package com.teamtwo.nullfunding.inquiry.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InquiryAttachmentDTO {
    
    private int attachNo; // 첨부파일 번호(시퀀스)
    private int inquiryNo; // 문의번호(참조)
    private String fileName; // 첨부파일 이름
    private String url; // 첨부파일 주소
    private String hashName; // 저장한파일명

    
}
