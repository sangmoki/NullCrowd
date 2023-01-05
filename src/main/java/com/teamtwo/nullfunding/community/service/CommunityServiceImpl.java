package com.teamtwo.nullfunding.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CommunityService implements CommunityService {

    private final CommunityMapper mapper;

    public CommunityService(CommunityMapper mapper) {
        this.mapper = mapper;
    }

    /* 해당 게시글 전체 갯수 조회용 메소드 */
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    /* 게시글 전체 조회용 메소드 */
    public List<CommunityDTO> selectCommunityList(SelectCriteria selectCriteria) {

        List<CommunityDTO> communityList = mapper.selectCommunityList(selectCriteria);

        return communityList;
    }

//    /* 게시글 상세 페이지 조회용 메소드 */
//    @Transactional
//    public CommunityDTO selectCommunityDetail(Long no) {
//        CommunityDTO communityDetail = null;
//
//        int result = mapper.incrementCommunityCount(no);
//
//        if (result > 0) {
//            communityDetail = mapper.selectCommunityDetail(no);
//        }
//
//        return communityDetail;
//    }
//
//    /* 해당 게시글의 전체 댓글 조회용 메소드 */
//    public List<ComCommentDTO> selectAllComCommentList(Long communityNo) {
//        List<ComCommentDTO> comCommentList = null;
//
//        comCommentList = mapper.selectComCommentList(communityNo);
//
//        return comCommentList;
//    }
//
//
//    /* 댓글 등록용 메소드 */
//    @Transactional
//    public List<ComCommentDTO> registComComment(ComCommentDTO registComComment) throws ComCommentRegistException {
//        List<ComCommentDTO> comCommentList = null;
//
//        int result = mapper.insertComComment(registComComment);
//
//        if (result > 0) {
//            comCommentList = mapper.selectComCommentList(registComComment.getRefCommunityNo());
//        } else {
//            throw new ComCommentRegistException("댓글 등록에 실패하셨습니다.");
//        }
//
//        return comCommentList;
//    }
//
//    /* 댓글 삭제용 메소드 */
//    @Transactional
//    public List<ComCommentDTO> removeComComment(ComCommentDTO removeComComment) throws ComCommentRemoveException {
//        List<ComCommentDTO> comCommentList = null;
//
//        int result = mapper.deleteComComment(removeComComment.getNo());
//
//        if (result > 0) {
//            comCommentList = mapper.selectComCommentList(removeComComment.getRefCommunityNo());
//        } else {
//            throw new ComCommentRemoveException("댓글 삭제에 실패하셨습니다.");
//        }
//
//        return comCommentList;
//    }
//
//    /* 게시글 등록용 메소드 */
//    @Transactional
//    public void registCommunity(CommunityDTO community) throws CommunityRegistException {
//        int result = mapper.insertCommunity(community);
//
//        if (!(result > 0)) {
//            throw new CommunityRegistException("게시글 등록에 실패하셨습니다.");
//        }
//    }
//
//    /* 전체 썸네일 게시글 조회용 메소드 */
//    public List<CommunityDTO> selectAllThumbnailList() {
//        List<CommunityDTO> thumbnailList = mapper.selectAllThumbnailList();
//
//        return thumbnailList;
//    }
//
//    /* 썸네일 게시글 등록용 메소드 */
//    @Transactional
//    public void registThumbnail(CommunityDTO thumbnail) throws ThumbnailRegistException {
//
//        int result = 0;
//
//        /* 먼저 community 테이블부터 insert 한다. */
//        int communityResult = mapper.insertThumbnailContent(thumbnail);
//
//        /* Attachment 리스트를 불러온다. */
//        List<AttachmentDTO> attachmentList = thumbnail.getAttachmentList();
//
//        /* fileList에 communityNo값을 넣는다. */
//        for (int i = 0; i < attachmentList.size(); i++) {
//            attachmentList.get(i).setRefCommunityNo(thumbnail.getNo());
//        }
//
//        /* Attachment 테이블에 list size만큼 insert 한다. */
//        int attachmentResult = 0;
//        for (int i = 0; i < attachmentList.size(); i++) {
//            attachmentResult += mapper.insertAttachment(attachmentList.get(i));
//        }
//
//        /* 게시글 추가 및 첨부파일 갯수 만큼 첨부파일 내용 insert에 실패 시 예외 발생 */
//        if (!(communityResult > 0 && attachmentResult == attachmentList.size())) {
//            throw new ThumbnailRegistException("사진 게시판 등록에 실패하셨습니다.");
//        }
//    }
//
//    /* 게시글 상세 페이지 조회용 메소드 */
//    public CommunityDTO selectThumbnailDetail(Long no) {
//        CommunityDTO thumbnailDetail = null;
//
//        int result = mapper.incrementCommunityCount(no);
//
//        if (result > 0) {
//            thumbnailDetail = mapper.selectThumbnailDetail(no);
//        }
//
//        return thumbnailDetail;
    }
}
