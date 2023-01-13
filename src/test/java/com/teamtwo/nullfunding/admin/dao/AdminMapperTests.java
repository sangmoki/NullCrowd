package com.teamtwo.nullfunding.admin.dao;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.admin.model.dao.AdminMemberMapper;
import com.teamtwo.nullfunding.config.MybatisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class})
public class AdminMapperTests {

    @Autowired
    private AdminMemberMapper mapper;

    @Test
    public void 매퍼_인터페이스_의존성_주입_테스트(){
        assertNotNull(mapper);
        System.out.println("projectMapper = " + mapper);
    }

    @Test
    void 프로젝트_승인_메소드_테스트(){

        int result =  mapper.confirmProject(60, 3);
        assertNotNull(result);

        assertEquals(3, result);

    }

}
