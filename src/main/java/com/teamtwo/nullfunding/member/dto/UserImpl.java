package com.teamtwo.nullfunding.member.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
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

    public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setDetails(MemberDTO member) {

        this.memCode = member.getMemCode();
        this.authCode = member.getAuthCode();
        this.memEmail = member.getMemEmail();
        this.memPwd = member.getMemPwd();
        this.isActive = member.getIsActive();
        this.regiDate = member.getRegiDate();
        this.nickName = member.getNickName();
        this.recentLogin = member.getRecentLogin();
        this.personalInfoDTO = member.getPersonalInfoDTO();
    }

    public int getMemCode() { return memCode; }

    public int getAuthCode() { return authCode; }

    public String getMemEmail() { return memEmail; }

    public String getMemPwd() { return memPwd; }

    public String getIsActive() { return isActive; }

    public Date getRegiDate() { return regiDate; }

    public String getNickName() { return nickName; }

    public Date getRecentLogin() { return recentLogin; }

    public PersonalInfoDTO getPersonalInfoDTO() { return personalInfoDTO; }

    @Override
    public String toString() {
        return "UserImpl{" +
                "memCode=" + memCode +
                ", authCode=" + authCode +
                ", memEmail='" + memEmail + '\'' +
                ", memPwd='" + memPwd + '\'' +
                ", isActive='" + isActive + '\'' +
                ", regiDate=" + regiDate +
                ", nickName='" + nickName + '\'' +
                ", recentLogin=" + recentLogin +
                ", personalInfoDTO=" + personalInfoDTO +
                '}';
    }
}
