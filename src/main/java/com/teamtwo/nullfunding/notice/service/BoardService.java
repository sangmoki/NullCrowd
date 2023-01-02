package com.teamtwo.nullfunding.notice.service;

import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;

import java.util.List;

public interface BoardService {

    // 모든 공지사항 리스트 조회하는 용도의 메서드
    List<NoticeDTO> selectAllNoticeList();

    // 공지사항 게시글 추가하는 용도의 메서드
    int insertNotice(NoticeDTO notice);

    // 공지사항 게시글 변경하는 용도의 메서드
    int updateNotice(NoticeDTO notice);

    // 공지사항 게시글 삭제하는 용도의 메서드
    int deleteNotice(int noticeNo);

    // 검색하여 공지사항 리스트 조회하는 용도의 메서드
    NoticeDTO selectChoiceNotice(int no);

    // 게시글 조회 시 조회 수 증가하는 용도의 메서드
    int incrementNoticeCount(int count);
}
