package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.common.Exception.member.MemberInsertException;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {


    void insertMember(MemberDTO member) throws MemberInsertException;

    int idDupCheck(String memEmail);

}
