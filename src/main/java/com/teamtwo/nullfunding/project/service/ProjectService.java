package com.teamtwo.nullfunding.project.service;


import com.teamtwo.nullfunding.project.model.dto.PJDetail;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface ProjectService {
    boolean requestProject(ProjectDTO projectDTO);

    List<PJDetail> selectAllProject();

    List<PJDetail> selectPreProject();

    int CalculateDday(Date date);

    PJDetail selectThisProject(int no);
}
