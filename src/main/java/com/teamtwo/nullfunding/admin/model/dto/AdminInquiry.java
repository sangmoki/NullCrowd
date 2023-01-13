package com.teamtwo.nullfunding.admin.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminInquiry {

    private int memCode;
    private String memEmail;
    private String name;
    private String phone;
    private Date inquiryDate;
    private String typeName;
    private String readYn;
}
