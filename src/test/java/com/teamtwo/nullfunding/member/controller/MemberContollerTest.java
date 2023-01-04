package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class MemberContollerTest {



    @Autowired
    private MemberController memberController;

    @Autowired
    private MemberService memberService;

    private MockMvc mockMvc; // 가짜 http 요청을 지원하는 객체

    @Autowired
    PasswordEncoder passwordEncoder;
    private WebApplicationContext context;

//    @BeforeEach
//    public void setup(){
//        mvc = MockMvcBuilders
//                .webAppContextSetup(this.context)
//                .apply(springSecurity())
//                .build();
//
//        Member member = new Member("csytest1", passwordEncoder.encode("1234qwer"), "test", false);
//        MemberDTO.save(member);
//    }
//    }


    @BeforeEach
    public void initMockMvc(){
        // 테스트 할 객체를 위한 가상의 Mock 객체 생성
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    @Disabled
    public void testInit1(){

        assertNotNull(memberController);
        assertNotNull(mockMvc);
    }

    @Test
    public void 로그인_컨트롤러_동작_테스트() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/member/login"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.forwardedUrl("content/member/login"))
                .andDo(print());
    }
    
}
