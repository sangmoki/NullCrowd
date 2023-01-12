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
        projectDTO.setRaiserCode(54);
        projectDTO.setTitle("겨울에는 스키가 빠질 수 없다!");
        projectDTO.setDescription("겨울에는 스키캠프 !");
        projectDTO.setFundGoal(4000000);
        projectDTO.setStartDate(new Date(122,11,30));
        projectDTO.setEndDate(new Date( 123,1,15));
        projectDTO.setMainImg("/img/thumbnail/2ski");
        projectDTO.setTel("010-1234-4321");
        projectDTO.setPjEmail("creba@naver.com");
        projectDTO.setRefundRule("스키캠프 시작 1주일 전에 환불 신청을 하시면 전액 환불을 해드립니다.");
        projectDTO.setVideoURL("https://www.youtube.com/watch?v=YFvXTUOWqlI");
        List<ProjectRewardDTO> projectRewardDTOList = new ArrayList<>();
        ProjectRewardDTO reward1 = new ProjectRewardDTO(0, 0,"리워드명1", 150000, "왕초보~초보코스."  );
        ProjectRewardDTO reward2 = new ProjectRewardDTO(0, 1, "리워드명2", 200000, "중급~고급코스"  );
        projectRewardDTOList.add(reward1);
        projectRewardDTOList.add(reward2);


        // when
        int result = projectMapper.requestProject(projectDTO);
        assertEquals(1, result);
        int result2 = 0;
        int result3 = 0;
        if(result == 1 ){

           result2 = projectMapper.insertRewards(projectRewardDTOList);
           result3 =  projectMapper.insertApproveProject();
        }

        assertEquals(1, result3);
        assertEquals(1, result2);
    }

//    @Test
//    void 프로젝트승인_등록_메소드_테스트(){
//
//        projectMapper.insertApproveProject();
//    }
}
