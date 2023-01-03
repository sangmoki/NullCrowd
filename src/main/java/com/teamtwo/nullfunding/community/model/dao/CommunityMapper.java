package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {

    public interface BoardMapper {
        int selectTotalCount(Map<String, String> searchMap);
        List<BoardDTO> selectBoardList(SelectCriteria selectCriteria);

        BoardDTO selectBoardDetail(Long no);

        List<ReplyDTO> selectComcommunityList(Long boardNo);

        int insertReply(ReplyDTO registReply);

        int deleteReply(Long no);

        int insertBoard(BoardDTO board);

//        List<BoardDTO> selectAllThumbnailList();

//        int insertThumbnailContent(BoardDTO thumbnail);

//        int insertAttachment(AttachmentDTO attachmentDTO);

//        int incrementBoardCount(Long no);

//        BoardDTO selectThumbnailDetail(Long no);
    }