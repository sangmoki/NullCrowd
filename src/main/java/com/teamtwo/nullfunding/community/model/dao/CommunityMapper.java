package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {

    // 전체 개수
    int selectTotalCount(Map<String, String> searchMap);

    // 모든 게시판 리스트 조회하는 용도의 메서드
    List<CommunityDTO> selectAllCommunityList(SelectCriteria selectCriteria);

    // 게시판 상세보기 페이지로 이동하는 용도의 메서드
    CommunityDTO selectCommunityDetail(int no);

    // 게시판 게시글 추가하는 용도의 메서드
    int insertCommunity(CommunityDTO community);

    // 게시판 게시글 변경하는 용도의 메서드
    int updateCommunity(CommunityDTO community);

    // 게시판 게시글 삭제하는 용도의 메서드
    int deleteCommunity(int no);

    // 게시글 조회 시 조회 수 증가하는 용도의 메서드
    int incrementCommunityCount(int no);

}