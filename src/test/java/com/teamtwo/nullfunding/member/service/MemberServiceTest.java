package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;

import java.util.Random;

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

    @Test
    public void getRandomCode() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < 7; i++) {                           // i에는 랜덤 생성하고 싶은 값의 '자리수+1'을 입력. 7입력 시 6자리 코드.
            if (random.nextBoolean()) {
                sb.append((char) (random.nextInt(26) + 65));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        System.out.println("sb.toString() = " + sb.toString());
    }
}