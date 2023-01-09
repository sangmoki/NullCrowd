package com.teamtwo.nullfunding.community.service;

import com.teamtwo.nullfunding.community.model.dao.CommunityMapper;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService {

    private CommunityMapper communityMapper;

    public CommunityServiceImpl(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    // 모든 게시판 게시글 조회하는 용도의 메서드
    @Override
    public List<CommunityDTO> selectAllCommunityList() {

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 원하는 데이터 포맷 지정
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        // 지정한 포맷으로 변환
        System.out.println(simpleDateFormat.format(nowDate));

        List<CommunityDTO> communityList = communityMapper.selectAllCommunityList();
        System.out.println(communityList);

        return communityList;

    }

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {
        return 0;
    }
}