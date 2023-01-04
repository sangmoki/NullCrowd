package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Test
    void loadUserByUsername() {


    String memEmail = "hg1540@naver.com";
    UserDetails user = memberService.loadUserByUsername(memEmail);
    assertNotNull(user);
        System.out.println("user = " + user);
    }
}