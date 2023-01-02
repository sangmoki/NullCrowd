package com.teamtwo.nullfunding.model.service;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class NoticeServiceTests {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void testInit(){

        assertNotNull(noticeService);
    }

    @Test
    public void 전체_공지_조회_메소드_테스트(){

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();

        assertNotNull(noticeList);
        noticeList.forEach(System.out::println);

    }

//    /* 공지사항 등록용 메소드 */
//    @Test
//    @Transactional
//    public void registNotice(NoticeDTO notice) throws NoticeRegistException {
//
//        int result = noticeService.insertNotice(notice);
//
//        if(!(result > 0)) {
//            throw new NoticeRegistException("공지사항 등록 실패 !");
//        }
//    }

//    /* 공지사항 수정용 메소드 */
//    @Test
//    public void 공지사항_수정용_메소드_테스트 throws NoticeModifyException {
//        int result = noticeService.updateNotice(notice);
//
//        if(!(result > 0)) {
//            throw new NoticeModifyException("공지사항 수정 실패 !");
//        }
//    }
//
//    /* 공지사항 삭제용 메소드 */
//    @Transactional
//    public void removeNotice(Long no) throws NoticeRemoveException {
//        int result = noticeService.deleteNotice(no);
//
//        if(!(result > 0)) {
//            throw new NoticeRemoveException("공지사항 삭제 실패 !");
//        }
//    }
//
//    /* 공지사항 상세 페이지 조회용 메소드 */
//    public NoticeDTO selectChoiceNotice(Long count) {
//        NoticeDTO choiceNotice = null;
//
//        int result = noticeService.incrementNoticeCount(count);
//
//        if(result > 0) {
//            choiceNotice = noticeService.selectChoiceNotice(count);
//        }
//
//        return choiceNotice;
//    }



}
