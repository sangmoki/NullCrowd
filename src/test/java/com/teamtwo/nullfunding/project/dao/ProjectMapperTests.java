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
//
//         //given
//        ProjectDTO projectDTO = new ProjectDTO();
//        projectDTO.setRaiserCode(59);
//        projectDTO.setTitle("여수 짚코스터");
//        projectDTO.setDescription("상공 50M의 높이에서 스릴을 만끽하세요!");
//        projectDTO.setFundGoal(1700000);
//        projectDTO.setStartDate(new Date(123,2,5));
//        projectDTO.setEndDate(new Date(123,3,5));
//        projectDTO.setMainImg("/img/thumbnail/17zip.jpg");
//        projectDTO.setTel("010-5555-5555");
//        projectDTO.setPjEmail("nullmember5@naver.com");
//        projectDTO.setRefundRule("5일 전까지 100% 환불가능합니다.");
//        projectDTO.setVideoURL("https://youtu.be/Wfcdycqn3w8");
//        List<ProjectRewardDTO> projectRewardDTOList = new ArrayList<>();
//        ProjectRewardDTO reward1 = new ProjectRewardDTO(0, 0,"리워드1", 20000, "짚코스터 기본 패키지");
//        ProjectRewardDTO reward2 = new ProjectRewardDTO(0, 1, "리워드2", 30000, "짚코스터 고급 패키지(기념품 포함)");
//        projectRewardDTOList.add(reward1);
//        projectRewardDTOList.add(reward2);
//
//
//        // when
//        int result = projectMapper.requestProject(projectDTO);
//        assertEquals(1, result);
//        int result2 = 0;
//        int result3 = 0;
//        if(result == 1 ){
//
//           result2 = projectMapper.insertRewards(projectRewardDTOList);
//           result3 =  projectMapper.insertApproveProject();
//        }
//
//        assertEquals(1, result3);
//        assertEquals(2, result2);
    }

    @Test
    void 퍼센트_시험_메소드(){

        List<PJDetail> projectList = projectMapper.selectAllProject();

        int fundGoal = projectList.get(0).getProjectDTO().getFundGoal();
        System.out.println("fundGoal = " + fundGoal);
        int raisedFund = projectList.get(0).getRaisedFund();
        System.out.println("raisedFund = " + raisedFund);


//        System.out.println("achievePercent = " + achievePercent);
    }

    @Test
    void 리워드_시험_메소드(){

        List<ProjectRewardDTO> projectRewardDTOList = projectMapper.selectRewards(49);

        System.out.println("projectRewardDTOList = " + projectRewardDTOList);
    }
}
