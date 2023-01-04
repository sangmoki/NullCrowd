package com.teamtwo.nullfunding.notice.service;

import com.teamtwo.nullfunding.notice.model.dao.NoticeMapper;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    // 모든 공지사항 게시글 조회하는 용도의 메서드
    @Override
    public List<NoticeDTO> selectAllNoticeList() { // 완료

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        // 원하는 데이터 포맷 지정
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        // 지정한 포맷으로 변환
        System.out.println(simpleDateFormat.format(nowDate));

        List<NoticeDTO> noticeList = noticeMapper.selectAllNoticeList();
        System.out.println(noticeList);

        return noticeList;


    }

    // 공지사항 검색하여 조회하는 용도의 메서드
    @Override
    public List<NoticeDTO> selectChoiceNotice() {
        return null;
    }

    // 공지사항 상세보기 용도의 메서드
    @Override
    public NoticeDTO selectNoticeDetail(int no) {

        return noticeMapper.selectNoticeDetail(no);
    }

    // 공지사항 추가하는 용도의 메서드
    @Override
    @Transactional
    public int insertNotice(NoticeDTO notice) {

        int result = 0;

        if(notice != null) {

            result = noticeMapper.insertNotice(notice);
        }

        return result;
    }

    // 공지사항 변경하는 용도의 메서드
    @Override
    public int updateNotice(NoticeDTO notice) {
        int result = 0;

        if(result <= 0){

            result = noticeMapper.updateNotice(notice);
        }

        return result;
    }

    // 공지사항 삭제하는 용도의 메서드
    @Override
    public int deleteNotice(int no) {

        int result = 0;

        if(result <= 0){

            result = noticeMapper.deleteNotice(no);
        }

        return result;
    }
    
    // 공지사항 상세보기 시 조회수 증가하는 용도의 메서드
    @Override
    public int incrementNoticeCount(int no) {

        int result = 0;

        if(result <= 0){

            result = noticeMapper.incrementNoticeCount(no);
        }

        return 0;
    }

}