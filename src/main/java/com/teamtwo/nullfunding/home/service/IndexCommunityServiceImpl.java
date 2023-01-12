package com.teamtwo.nullfunding.home.service;

import com.teamtwo.nullfunding.home.dao.IndexMapper;
import com.teamtwo.nullfunding.home.dto.IndexCommunityDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IndexCommunityServiceImpl {

    public List<IndexCommunityDTO> indexCommunity() { // 완료

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 원하는 데이터 포맷 지정
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<IndexCommunityDTO> indexCommunityList = IndexMapper.indexCommunity();

        return indexCommunityList;
    }
}
