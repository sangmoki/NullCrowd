package com.teamtwo.nullfunding.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {

    String selectMemberById(String memEmail);
}
