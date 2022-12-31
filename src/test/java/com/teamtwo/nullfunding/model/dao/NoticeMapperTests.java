package com.teamtwo.nullfunding.model.dao;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.notice.model.dao.NoticeMapper;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class NoticeMapperTests {

    @Autowired
    private NoticeMapper noticeMapper;

    @Test
    @DisplayName("Test")
    public void 매퍼_인터페이스_의존성_주입_테스트(){

        assertNotNull(noticeMapper);
        System.out.println("noticeMapper = " + noticeMapper);
    }

    @Test
    public void 전체_메뉴_조회용_매핑_테스트(){

        List<NoticeDTO> noticeList = noticeMapper.selectAllNoticeList();

        assertNotNull(noticeList);
        noticeList.forEach(notice -> System.out.println("noticeList = " + notice));
    }



}
