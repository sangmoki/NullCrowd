package com.teamtwo.nullfunding.member.dao;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {
    MemberDTO findMemberById(String memEmail);



//    public String selectMemberById(String memEmail);
//
    public int insertMember(MemberDTO member);

    public int idDupCheck(String memEmail);
}

