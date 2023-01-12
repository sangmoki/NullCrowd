package com.teamtwo.nullfunding.project.service;

import com.teamtwo.nullfunding.NullfundingApplication;
import com.teamtwo.nullfunding.config.MybatisConfig;
import com.teamtwo.nullfunding.project.model.dto.PJDetail;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = { NullfundingApplication.class, MybatisConfig.class })
public class ProjectServiceTests {

    @Autowired
    private ProjectService projectService;


    @Test
    void 프로젝트_등록_메소드_테스트(){

        // given
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setRaiserCode(10);
        projectDTO.setTitle("프젝이름1");
        projectDTO.setDescription("프젝설명");
        projectDTO.setFundGoal(1000000);
        projectDTO.setStartDate(new Date(2023-01-26));
        projectDTO.setEndDate(new Date((2023-02-03)));
        projectDTO.setMainImg("/img/projectImg/07e61069cd37409dafd8d7b6b6a0870c.png");
        projectDTO.setTel("010-1234-5432");
        projectDTO.setPjEmail("creba@naver.com");
        projectDTO.setRefundRule("환불정책입니다.");
        projectDTO.setVideoURL("https://www.youtube.com/watch?v=rXPfovXw2tw&list=RDrXPfovXw2tw&start_radio=1");

        List<ProjectRewardDTO> projectRewardDTO = new ArrayList<>();
        ProjectRewardDTO reward1 = new ProjectRewardDTO(0, "리워드명1", 10000, "만원짜리리워드입니다."  );
        ProjectRewardDTO reward2 = new ProjectRewardDTO(0, "리워드명2", 20000, "이만원짜리요" );
        projectRewardDTO.add(reward1);
        projectRewardDTO.add(reward2);


        // when
        boolean result = projectService.requestProject(projectDTO);
        assertEquals(true, result);
    }

    @Test
    void 날짜_테스트(){

        List<PJDetail> ProjectList = projectService.selectAllProject();
        System.out.println("ProjectList = " + ProjectList);

        for(PJDetail pj : ProjectList){

            System.out.println("Dday = " + pj.getRemainDate());
        }
    }

    @Test
    void Pre프로젝트_들고오는_메소드_테스트(){

        List<PJDetail> preProject =  projectService.selectPreProject();
        assertNotNull(preProject);
        System.out.println("preProject = " + preProject);

    }

    @Test
    void 모집금액_테스트(){

        List<PJDetail> ProjectList = projectService.selectAllProject();
        System.out.println("ProjectList = " + ProjectList);


        for(PJDetail pj : ProjectList){

            System.out.println("Percent = " + pj.getAchievePercent());
        }
    }
}
