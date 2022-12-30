package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MemberDTO {

    private int memCode;
    private int authCode;
    private String memEmail;
    private String memPwd;
    private String isActive;
    private Date regiDate;
    private String nickName;
    private Date recentLogin;
    private String phone;
    private Date birthdate;
    private String address;
    private String gender;
}