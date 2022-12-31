package com.teamtwo.nullfunding.controller;

import com.teamtwo.nullfunding.config.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.notice.controller.NoticeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class NoticeContollerTest {
    
    @Autowired
    private NoticeController noticeController;
    
    private MockMvc mockMvc; // 가짜 http 요청을 지원하는 객체
    
    @BeforeEach
    public void initMockMvc(){
        
        // 테스트 할 객체를 위한 가상의 Mock 객체 생성
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController).build();
    }

    @Test
    @Disabled
    public void testInit1(){

        assertNotNull(noticeController);
        assertNotNull(mockMvc);
    }

    @Test
    public void 전체_공지_조회용_컨트롤러_동작_테스트() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/notice/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("notice/list"))
                .andDo(print());
    }
    
}
