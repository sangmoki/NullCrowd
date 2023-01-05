package com.teamtwo.nullfunding.project.controller;

import com.teamtwo.nullfunding.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/makeProject")
    public String makeProject(){
        String projectPage = "content/project/makePJ1";

        return projectPage;
    }
}
