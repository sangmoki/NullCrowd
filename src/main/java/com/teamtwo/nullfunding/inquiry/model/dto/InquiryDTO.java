package com.teamtwo.nullfunding.inquiry.model.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InquiryDTO {

    private int inquiryNo; // 문의 번호(시퀀스)
    private int memCode; // 회원 번호(참조키)
    private String memEmail; // 회원 이메일
    private String type; // 문의 타입 (1, 2, 3)
    private String inquiryContent; // 문의 내용
    private Date inquiryDate; // 문의 요청일
    private String readYn;


}
