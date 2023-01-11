package com.teamtwo.nullfunding.project.model.dao;

import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProjectMapper {
    void requestProject(ProjectDTO projectDTO);
}
