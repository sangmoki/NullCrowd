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

    @Autowired // NoticeService 인터페이스의 sql문을 읽어와 의존주입으로 객체 생성.
    private NoticeMapper noticeMapper;

    // 모든 공지사항 게시글 조회하는 용도의 메서드
    @Override
    public List<NoticeDTO> selectAllNoticeList() {

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

    // 공지사항 게시글 추가하는 용도의 메서드
    @Override
    @Transactional
    public int insertNotice(NoticeDTO notice){

        int result = 0;

    return result;
    }

    // 공지사항 게시글 수정하는 용도의 메서드
    @Override
    @Transactional
    public int updateNotice(NoticeDTO notice){

        return 0;
    }

    // 공지번호를 입력받아 게시글을 삭제하는 용도의 메서드
    @Override
    @Transactional
    public int deleteNotice(int noticeNo){

        return 0;
    }

    // 공지사항 게시글의 상세 페이지를 조회하는 용도의 메서드
    // 게시글 클릭 시 조회수가 증가한다.
    @Override
    public NoticeDTO selectChoiceNotice(int no) {
        NoticeDTO noticeDetail = null;

        int result = noticeMapper.incrementNoticeCount(no);

        if(result > 0) {
            noticeDetail = noticeMapper.selectChoiceNotice(no);
        }

        return noticeDetail;
    }

    @Override
    public int incrementNoticeCount(int no) {

        return 0;
    }

}