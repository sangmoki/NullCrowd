package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.ComCommentDTO;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {

    /* 해당 게시글 전체 갯수 조회 */
    int selectTotalCount(Map<String, String> searchMap);

    /* 게시글 전체 조회 */
    List<CommunityDTO> selectCommunityList(SelectCriteria selectCriteria);

    /* 게시글 상세 페이지 조회 */
    CommunityDTO selectCommunityDetail(int no);

    /* 해당 게시글의 전체 댓글 조회 */
    List<ComCommentDTO> selectComCommentList(int articleNo);

    /* 댓글 등록 */
    int insertComComment(ComCommentDTO insertComComment);

    /* 댓글 삭제 */
    int deleteComComment(int no);

    /* 게시글 등록 */
    int insertCommunity(CommunityDTO community);

    /* 게시글 수정 */
    int updateCommunity(CommunityDTO community);

    /* 게시글 삭제 */
    int deleteCommunity(int no);

    // 조회 수 증가?
    int incrementCommunityCount(int no);

}