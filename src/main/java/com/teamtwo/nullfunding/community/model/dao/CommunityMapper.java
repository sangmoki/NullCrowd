package com.teamtwo.nullfunding.community.model.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {

    public interface CommunityMapper {
        int selectTotalCount(Map<String, String> searchMap);
        List<CommunityDTO> selectCommunityList(SelectCriteria selectCriteria);

        CommunityDTO selectCommunityDetail(Long no);

        List<ComCommentDTO> selectComcommunityList(Long communityNo);

        int insertComComment(ComCommentDTO registComComment);

        int deleteComComment(Long no);

        int insertCommunity(CommunityDTO community);

//        List<CommunityDTO> selectAllThumbnailList();

//        int insertThumbnailContent(CommunityDTO thumbnail);

//        int insertAttachment(AttachmentDTO attachmentDTO);

//        int incrementCommunityCount(Long no);

//        CommunityDTO selectThumbnailDetail(Long no);
    }