package com.teamtwo.nullfunding.member.dto;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.util.Date;


public class UserImpl extends User {

    private int memCode;
    private int authCode;
    private String memEmail;
    private String memPwd;
    private String isActive;
    private Date regiDate;
    private String nickName;
    private Date recentLogin;
    private PersonalInfoDTO personalInfoDTO;


}
