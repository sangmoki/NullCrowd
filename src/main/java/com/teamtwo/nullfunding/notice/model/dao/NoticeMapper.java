package com.teamtwo.nullfunding.notice.model.dao;

import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> selectAllNoticeList();
}
