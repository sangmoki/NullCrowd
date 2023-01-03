package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {

    List<CommunityDTO> selectAllNoticeList();
}
