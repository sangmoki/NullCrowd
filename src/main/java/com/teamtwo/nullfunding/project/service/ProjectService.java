package com.teamtwo.nullfunding.project.service;


import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

public interface ProjectService {
    boolean requestProject(ProjectDTO projectDTO);
}
