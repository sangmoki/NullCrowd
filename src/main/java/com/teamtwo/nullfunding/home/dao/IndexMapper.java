package com.teamtwo.nullfunding.home.dao;

import com.teamtwo.nullfunding.home.dto.IndexCommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexMapper {

    List<IndexCommunityDTO> indexCommunity();


}
