package com.teamtwo.nullfunding.home.service;

import com.teamtwo.nullfunding.home.dao.IndexMapper;
import com.teamtwo.nullfunding.home.dto.IndexCommunityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IndexCommunityServiceImpl implements IndexCommunityService {

    IndexMapper indexMapper;

    @Autowired
    public IndexCommunityServiceImpl(IndexMapper indexMapper) {
        this.indexMapper = indexMapper;
    }

    public List<IndexCommunityDTO> indexCommunity() { // 완료

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 원하는 데이터 포맷 지정
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<IndexCommunityDTO> indexCommunityList = indexMapper.indexCommunity();

        return indexCommunityList;
    }
}
