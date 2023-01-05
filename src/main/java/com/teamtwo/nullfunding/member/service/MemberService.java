package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface MemberService extends UserDetailsService {
    List<MemberDTO> getMemberList();
}
