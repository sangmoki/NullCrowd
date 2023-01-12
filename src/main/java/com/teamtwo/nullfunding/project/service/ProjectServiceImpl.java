package com.teamtwo.nullfunding.project.service;

import com.teamtwo.nullfunding.project.model.dao.ProjectMapper;
import com.teamtwo.nullfunding.project.model.dto.PJDetail;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;


    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public int CalculateDday(Date date){

        LocalDate now = LocalDate.now();
        LocalDate standardDate = date.toLocalDate();
        System.out.println("standardDate = " + standardDate);

        int dDay =  (int)ChronoUnit.DAYS.between(standardDate, now);
        //standardDate를 기준으로 now까지 얼마나 가야하는지.

        return Math.abs(dDay);
    }

    @Override
    @Transactional
    public boolean requestProject(ProjectDTO projectDTO) {


        int result2 = projectMapper.requestProject(projectDTO);
        int result3 = 0;

        // 프로젝트가 성공적으로 등록되면 Rewards리스트도 삽입
        if(result2 == 1){result3 = projectMapper.insertRewards(projectDTO.getProjectRewardDTOList());}

        boolean result = (result3 == projectDTO.getProjectRewardDTOList().size()) ? true : false;

        if(result == true){

            int result4 = projectMapper.insertApproveProject();

            result = (result4 == 1) ? true : false;

        }

        return result;
    }

    @Override
    @Transactional
    public List<PJDetail> selectAllProject() {

        List<PJDetail> ProjectList = projectMapper.selectAllProject();
        for(int i = 0; i < ProjectList.size(); i++){

//            ProjectList.get(i).setRemainDate();
//            ProjectList.get(i).setAchievePercent();

        }

        return ProjectList;
    }

    @Override
    @Transactional
    public List<PJDetail> selectPreProject() {

        List<PJDetail> preProjectList = projectMapper.selectPreProject();

        return preProjectList;
    }
}
