package com.teamtwo.nullfunding.member.dao;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

    @SpringBootTest
    @ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
    public class MemberMapperTest {

        @Autowired
        private MemberMapper memberMapper;

        @Test
        public void 매퍼_인터페이스_의존성_주입_테스트(){
            assertNotNull(memberMapper);
            System.out.println("memberMapper = " + memberMapper);
        }
        @Test
        void findMemberById() {
            MemberDTO member = memberMapper.findMemberById("t121212est@naver.com");
            assertNotNull(member);
            System.out.println("member = " + member);
        }


        @Test
        void insertMember() {

        }

        @Test
        void idDupCheck(){
            int result = memberMapper.idDupCheck("test@n11aver.com");
            assertNotNull(result);
            System.out.println("result = " + result);
        }
    }