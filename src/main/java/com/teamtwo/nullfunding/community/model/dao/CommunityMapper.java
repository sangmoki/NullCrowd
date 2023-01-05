package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {

    // 모든 게시판 리스트 조회하는 용도의 메서드
    List<CommunityDTO> selectAllCommunityList();
}