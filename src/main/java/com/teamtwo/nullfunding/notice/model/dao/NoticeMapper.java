package com.teamtwo.nullfunding.notice.model.dao;

import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 모든 공지사항 리스트 조회하는 용도의 메서드
    List<NoticeDTO> selectAllNoticeList();

    // 공지사항 게시글 추가하는 용도의 메서드
    int insertNotice(NoticeDTO notice);

    // 공지사항 게시글 변경하는 용도의 메서드
    int updateNotice(NoticeDTO notice);

    // 공지사항 게시글 삭제하는 용도의 메서드
    int deleteNotice(Long noticeNo);

    // 검색하여 공지사항 리스트 조회하는 용도의 메서드
    NoticeDTO selectChoiceNotice(Long count);

    // 게시글 조회 시 조회 수 증가하는 용도의 메서드
    int incrementNoticeCount(Long count);
}
