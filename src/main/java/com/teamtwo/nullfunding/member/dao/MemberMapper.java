package com.teamtwo.nullfunding.member.dao;

import com.teamtwo.nullfunding.member.dto.FundRaiserDTO;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.MembershipDTO;
import com.teamtwo.nullfunding.member.dto.PersonalInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper {
    MemberDTO findMemberById(String memEmail);

    public int insertMember(MemberDTO member);

    public int idDupCheck(String memEmail);

    public int nickDupCheck(String nickName);

    public int insertPersonalInfo(PersonalInfoDTO personalInfoDTO);

    int insertFundRaiser(FundRaiserDTO fundRaiserDTO);

    List<MembershipDTO> getMembershipData(int memberNo);

    MembershipDTO setMembershipData(Map<String, Object> membership);

}

