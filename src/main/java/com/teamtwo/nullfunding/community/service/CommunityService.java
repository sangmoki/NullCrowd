package com.teamtwo.nullfunding.community.service;

import com.teamtwo.nullfunding.common.Exception.community.*;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.ComCommentDTO;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CommunityService {
    // 전체 개수
    int selectTotalCount(Map<String, String> searchMap);

    // 모든 게시판 리스트 조회하는 용도의 메서드
    List<CommunityDTO> selectAllCommunityList(SelectCriteria selectCriteria);

    // 게시판 상세보기 페이지로 이동하는 용도의 메서드
    CommunityDTO selectCommunityDetail(int no);

    /* 해당 게시글의 전체 댓글 조회용 메소드 */
    List<ComCommentDTO> selectAllComCommentList(Long articleNo);

    /* 댓글 등록용 메소드 */
    @Transactional
    List<ComCommentDTO> insertComComment(ComCommentDTO insertComComment) throws ComCommentRegistException;

    /* 댓글 삭제용 메소드 */
    @Transactional
    List<ComCommentDTO> deleteComComment(ComCommentDTO deleteComComment) throws ComCommentDeleteException;

    // 게시판 게시글 추가하는 용도의 메서드
    int insertCommunity(CommunityDTO community) throws CommunityRegistException;

    // 게시판 게시글 변경하는 용도의 메서드
    int updateCommunity(CommunityDTO community) throws CommunityUpdateException;

    // 게시판 게시글 삭제하는 용도의 메서드
    int deleteCommunity(int no);

    /* 게시글 삭제용 메소드 */
    @Transactional
    int deleteCommunity(Long no) throws CommunityDeleteException;
}
