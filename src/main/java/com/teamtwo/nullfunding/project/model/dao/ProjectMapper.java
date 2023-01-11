package com.teamtwo.nullfunding.project.model.dao;

import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    int requestProject(ProjectDTO projectDTO);

    int insertRewards(List<ProjectRewardDTO> projectRewardDTOList);
}
