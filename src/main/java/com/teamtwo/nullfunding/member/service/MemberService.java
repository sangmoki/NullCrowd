package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {


    int insertMember(MemberDTO member);

    int idDupCheck(String memEmail);

    int nickDupCheck(String nickName);
}
