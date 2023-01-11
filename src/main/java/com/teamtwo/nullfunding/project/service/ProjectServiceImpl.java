package com.teamtwo.nullfunding.project.service;

import com.teamtwo.nullfunding.project.model.dao.ProjectMapper;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public boolean requestProject(ProjectDTO projectDTO) {


        int result2 = projectMapper.requestProject(projectDTO);
        int result3 = 0;

        // 프로젝트가 성공적으로 등록되면 Rewards리스트도 삽입
        if(result2 == 1){result3 = projectMapper.insertRewards(projectDTO.getProjectRewardDTOList());}

        boolean result = (result3 == projectDTO.getProjectRewardDTOList().size()) ? true : false;

        return result;
    }
}
