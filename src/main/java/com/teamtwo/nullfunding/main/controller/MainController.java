package com.teamtwo.nullfunding.main.controller;

import com.teamtwo.nullfunding.project.model.dto.PJDetail;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import com.teamtwo.nullfunding.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    ProjectService projectService;

    @Autowired
    public MainController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = {"/", "/main"})
    public ModelAndView defaultLocation(ModelAndView mv){

       List<PJDetail> allProject = projectService.selectAllProject();  // 얘는 fundable 'Y'인 애를 가져오자
        mv.addObject("allProject", allProject);

        List<PJDetail> preProject = projectService.selectPreProject();



        mv.setViewName("/index");
        return mv;
    }

    @PostMapping("/")

    public String redirectMain(){

        return "redirect:/";
    }
}
