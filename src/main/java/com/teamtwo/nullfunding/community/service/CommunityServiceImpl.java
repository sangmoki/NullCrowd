package com.teamtwo.nullfunding.community.service;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dao.CommunityMapper;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = communityMapper.selectTotalCount(searchMap);

        return result;
    }

    // 모든 게시판 게시글 조회하는 용도의 메서드
    @Override
    public List<CommunityDTO> selectAllCommunityList(SelectCriteria selectCriteria) { // 완료

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 원하는 데이터 포맷 지정
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<CommunityDTO> communityList = communityMapper.selectAllCommunityList(selectCriteria);

        return communityList;
    }

    // 게시판 상세보기 용도의 메서드
    @Transactional
    @Override
    public CommunityDTO selectCommunityDetail(int no) {
        CommunityDTO communityDetail = null;

        int result = communityMapper.incrementCommunityCount(no);

        if (result > 0) {
            communityDetail = communityMapper.selectCommunityDetail(no);
            System.out.println("communityDetail ===============================> " + communityDetail);
        }

        return communityDetail;
    }

    // 게시판 추가하는 용도의 메서드
    @Override
    @Transactional
    public int insertCommunity(CommunityDTO community) {

        int result = 0;

        if (community != null) {

            result = communityMapper.insertCommunity(community);
        }

        return result;
    }

    // 게시판 변경하는 용도의 메서드
    @Override
    @Transactional
    public int updateCommunity(CommunityDTO community) {
        int result = 0;

        if (community != null) {

            result = communityMapper.updateCommunity(community);
        }

        return result;
    }

    // 게시판 삭제하는 용도의 메서드
    @Override
    @Transactional
    public int deleteCommunity(int no) {

        int result = communityMapper.deleteCommunity(no);

        return result;

    }
}