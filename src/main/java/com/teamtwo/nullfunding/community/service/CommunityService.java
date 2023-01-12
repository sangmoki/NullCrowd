package com.teamtwo.nullfunding.community.service;

import com.teamtwo.nullfunding.common.Exception.community.*;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dao.CommunityMapper;
import com.teamtwo.nullfunding.community.model.dto.ComCommentDTO;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommunityService {

    private final CommunityMapper mapper;

    public CommunityService(CommunityMapper mapper) {

        this.mapper = mapper;
    }

    /* 해당 게시글 전체 갯수 조회 */
    public int selectTotalCount(Map<String, String> searchMap) {
        System.out.println("해당 게시글 전체 갯수 조회 ");

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    /* 게시글 전체 조회 */
    public List<CommunityDTO> selectCommunityList(SelectCriteria selectCriteria) {
        System.out.println("게시글 전체 조회");

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<CommunityDTO> communityList = mapper.selectCommunityList(selectCriteria);

        return communityList;
    }

    /* 게시글 상세 페이지 조회 */
    @Transactional
    public CommunityDTO selectCommunityDetail(int no) {
        System.out.println("게시글 상세 페이지 조회");

        CommunityDTO communityDetail = null;

        int result = mapper.incrementCommunityCount(no);

        if (result > 0) {
            communityDetail = mapper.selectCommunityDetail(no);
        }

        return communityDetail;
    }

    /* 해당 게시글의 전체 댓글 조회 */
    public List<ComCommentDTO> selectAllComCommentList(int articleNo) {
        System.out.println("해당 게시글의 전체 댓글 조회 ");

        List<ComCommentDTO> comCommentList = null;

        comCommentList = mapper.selectComCommentList(articleNo);

        return comCommentList;
    }

    /* 댓글 등록 */
    @Transactional
    public List<ComCommentDTO> registComComment(ComCommentDTO registComComment) throws ComCommentRegistException {
        System.out.println("댓글 등록");

        List<ComCommentDTO> comCommentList = null;

        int result = mapper.insertComComment(registComComment);

        if (result > 0) {
            comCommentList = mapper.selectComCommentList(registComComment.getArticleNo());
        } else {
            throw new ComCommentRegistException("댓글 등록에 실패하셨습니다.");
        }

        return comCommentList;
    }

    /* 댓글 삭제 */
    @Transactional
    public List<ComCommentDTO> removeComComment(ComCommentDTO removeComComment) throws ComCommentRemoveException {
        System.out.println("댓글 삭제");

        List<ComCommentDTO> comCommentList = null;

        int result = mapper.deleteComComment(removeComComment.getCommentNo());

        if (result > 0) {
            comCommentList = mapper.selectComCommentList(removeComComment.getArticleNo());
        } else {
            throw new ComCommentRemoveException("댓글 삭제에 실패하셨습니다.");
        }

        return comCommentList;
    }

    /* 게시글 등록 */
    @Transactional
    public void registCommunity(CommunityDTO community) throws CommunityRegistException {
        System.out.println("게시글 등록");

        int result = mapper.insertCommunity(community);

        if (!(result > 0)) {
            throw new CommunityRegistException("게시글 등록에 실패하셨습니다.");
        }
    }

    /* 게시글 수정 */
    @Transactional
    public void modifyCommunity(CommunityDTO community) throws CommunityModifyException {
        System.out.println("게시글 수정");

        int result = mapper.updateCommunity(community);

        if (!(result > 0)) {
            throw new CommunityModifyException("공지사항 수정에 실패하셨습니다.");
        }
    }

    /* 게시글 삭제 */
    @Transactional
    public void removeCommunity(int no) throws CommunityRemoveException {
        System.out.println("게시글 삭제");

        int result = mapper.deleteCommunity(no);

        if (!(result > 0)) {
            throw new CommunityRemoveException("공지사항 삭제에 실패하셨습니다.");
        }
    }
}