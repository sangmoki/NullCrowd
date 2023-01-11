package com.teamtwo.nullfunding.admin.model.dto;

import com.teamtwo.nullfunding.member.dto.FundRaiserDTO;
import com.teamtwo.nullfunding.member.dto.PersonalInfoDTO;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class AdminMemberDTO {

    private int memCode;
    private String authName;
    private String memEmail;
    private String memPwd;
    private String isActive;
    private Date regiDate;
    private String nickName;
    private Date recentLogin;
    private PersonalInfoDTO personalInfoDTO;
    private FundRaiserDTO fundRaiserDTO;

}