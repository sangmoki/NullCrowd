package com.teamtwo.nullfunding.project.dao;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.project.model.dao.ProjectMapper;
import com.teamtwo.nullfunding.project.model.dto.PJDetail;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class})
public class ProjectMapperTests {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void 매퍼_인터페이스_의존성_주입_테스트(){
        assertNotNull(projectMapper);
        System.out.println("projectMapper = " + projectMapper);
    }

    @Test
    void 프로젝트_들고오는_메소드_테스트(){

        List<PJDetail> AllProject =  projectMapper.selectAllProject();
        assertNotNull(AllProject);
        System.out.println("AllProject = " + AllProject);

    }

    @Test
    void Pre프로젝트_들고오는_메소드_테스트(){

        List<PJDetail> preProject =  projectMapper.selectPreProject();
        assertNotNull(preProject);
        System.out.println("preProject = " + preProject);

    }
    @Test
    void 프로젝트_등록_메소드_테스트(){

         //given
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setRaiserCode(10);
        projectDTO.setTitle("Hell 2023! 즐거운 신년 파티");
        projectDTO.setDescription("프젝설명");
        projectDTO.setFundGoal(3000000);
        projectDTO.setStartDate(new Date(122,11,30));
        projectDTO.setEndDate(new Date( 123,1,15));
        projectDTO.setMainImg("/img/thumbnail/07e61069cd37409dafd8d7b6b6a0870c.png");
        projectDTO.setTel("010-1234-5432");
        projectDTO.setPjEmail("creba@naver.com");
        projectDTO.setRefundRule("환불정책입니다.");
        projectDTO.setVideoURL("https://www.youtube.com/watch?v=rXPfovXw2tw&list=RDrXPfovXw2tw&start_radio=1");

        List<ProjectRewardDTO> projectRewardDTOList = new ArrayList<>();
        ProjectRewardDTO reward1 = new ProjectRewardDTO(0, 0,"리워드명1", 10000, "만원짜리리워드입니다."  );
        ProjectRewardDTO reward2 = new ProjectRewardDTO(0, 1, "리워드명2", 20000, "이만원짜리요"  );
        projectRewardDTOList.add(reward1);
        projectRewardDTOList.add(reward2);


        // when
        int result = projectMapper.requestProject(projectDTO);
        assertEquals(1, result);
        int result2 = 0;
        if(result == 1 ){

           result2 = projectMapper.insertRewards(projectRewardDTOList);
        }

        assertEquals(2, result2);
    }
}
