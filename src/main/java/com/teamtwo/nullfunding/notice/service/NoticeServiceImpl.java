package com.teamtwo.nullfunding.notice.service;

import com.teamtwo.nullfunding.notice.model.dao.NoticeMapper;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements BoardService{

    @Autowired // BoardService 인터페이스의 sql문을 읽어와 의존주입으로 객체 생성.
    private NoticeMapper noticeMapper;

    // 모든 공지사항 게시글 조회하는 용도의 메서드
    @Override
    public List<NoticeDTO> selectAllNoticeList() {


        return null;
    }

    // 공지사항 게시글 추가하는 용도의 메서드
    @Override
    public int insertNotice(NoticeDTO notice) {
        return 0;
    }

    // 공지사항 게시글 수정하는 용도의 메서드
    @Override
    public int updateNotice(NoticeDTO notice) {
        return 0;
    }

    // 공지번호를 입력받아 게시글을 삭제하는 용도의 메서드
    @Override
    public int deleteNotice(int noticeNo) {
        return 0;
    }

    // 공지사항 게시글의 상세 페이지를 조회하는 용도의 메서드
    @Override
    public NoticeDTO selectChoiceNotice(int no) {
        return null;
    }

    // 게시글을 클릭할 때마다 조회수가 증가하는 용도의 메서드
    @Override
    public int incrementNoticeCount(int count) {
        return 0;
    }
}