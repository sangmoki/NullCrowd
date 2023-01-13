package com.teamtwo.nullfunding.community.dao;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.community.model.dao.CommunityMapper;
import com.teamtwo.nullfunding.config.MybatisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class CommunityMapperTest {

    @Autowired
    private CommunityMapper communityMapper;




}
