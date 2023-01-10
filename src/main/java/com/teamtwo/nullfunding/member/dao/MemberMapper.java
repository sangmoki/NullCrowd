package com.teamtwo.nullfunding.member.dao;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberDTO findMemberById(String memEmail);

    public int insertMember(MemberDTO member);

    public int idDupCheck(String memEmail);

    public int nickDupCheck(String nickName);
}

