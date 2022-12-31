package com.teamtwo.nullfunding.notice.service;

import com.teamtwo.nullfunding.common.exception.notice.NoticeModifyException;
import com.teamtwo.nullfunding.common.exception.notice.NoticeRegistException;
import com.teamtwo.nullfunding.common.exception.notice.NoticeRemoveException;
import com.teamtwo.nullfunding.notice.model.dao.NoticeMapper;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper mapper;

    public NoticeService(NoticeMapper mapper) {
        this.mapper = mapper;
    }

    /* 공지사항 전체 목록 조회용 메소드 */
    public List<NoticeDTO> selectAllNoticeList() {
        List<NoticeDTO> noticeList = mapper.selectAllNoticeList();

        return noticeList;
    }

    /* 공지사항 등록용 메소드 */
    @Transactional
    public void registNotice(NoticeDTO notice) throws NoticeRegistException {

        int result = mapper.insertNotice(notice);

        if(!(result > 0)) {
            throw new NoticeRegistException("공지사항 등록 실패 !");
        }
    }

    /* 공지사항 수정용 메소드 */
    @Transactional
    public void updateNotice(NoticeDTO notice) throws NoticeModifyException {
        int result = mapper.updateNotice(notice);

        if(!(result > 0)) {
            throw new NoticeModifyException("공지사항 수정 실패 !");
        }
    }

    /* 공지사항 삭제용 메소드 */
    @Transactional
    public void removeNotice(Long no) throws NoticeRemoveException {
        int result = mapper.deleteNotice(no);

        if(!(result > 0)) {
            throw new NoticeRemoveException("공지사항 삭제 실패 !");
        }
    }

    /* 공지사항 상세 페이지 조회용 메소드 */
    public NoticeDTO selectChoiceNotice(Long count) {
        NoticeDTO choiceNotice = null;

        int result = mapper.incrementNoticeCount(count);

        if (result > 0) {
            choiceNotice = mapper.selectChoiceNotice(count);
        }

        return choiceNotice;
    }

}