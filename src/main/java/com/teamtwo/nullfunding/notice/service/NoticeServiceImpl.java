package com.teamtwo.nullfunding.notice.service;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.notice.model.dao.NoticeMapper;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = noticeMapper.selectTotalCount(searchMap);

        return result;
    }

    // 모든 공지사항 게시글 조회하는 용도의 메서드
    @Override
    public List<NoticeDTO> selectAllNoticeList(SelectCriteria selectCriteria) { // 완료

        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd a HH:mm:ss");

        List<NoticeDTO> noticeList = noticeMapper.selectAllNoticeList(selectCriteria);

        return noticeList;
    }

    // 공지사항 상세보기 용도의 메서드
    @Transactional
    @Override
    public NoticeDTO selectNoticeDetail(int no) {
        NoticeDTO noticeDetail = null;

        int result = noticeMapper.incrementNoticeCount(no);

        if(result > 0){
            noticeDetail = noticeMapper.selectNoticeDetail(no);
            System.out.println("noticeDetail ===============================> " + noticeDetail);
        }

        return noticeDetail;
    }

    // 공지사항 추가하는 용도의 메서드
    @Override
    @Transactional
    public int insertNotice(NoticeDTO notice) {

        int result = 0;

        if(notice != null) {

            result = noticeMapper.insertNotice(notice);
        }

        System.out.println("result =============== " + result);
        return result;
    }

    // 공지사항 변경하는 용도의 메서드
    @Override
    @Transactional
    public int updateNotice(NoticeDTO notice) {
        int result = 0;

        if(notice != null){

            result = noticeMapper.updateNotice(notice);
        }

        return result;
    }

    // 공지사항 삭제하는 용도의 메서드
    @Override
    @Transactional
    public int deleteNotice(int no) {

        int result = noticeMapper.deleteNotice(no);

        return result;

    }


}