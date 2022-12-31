package com.teamtwo.nullfunding.member.dto;

            roles.add(new SimpleGrantedAuthority(role));
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
}
