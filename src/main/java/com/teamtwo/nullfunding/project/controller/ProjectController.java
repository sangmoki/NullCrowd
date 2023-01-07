package com.teamtwo.nullfunding.project.controller;

import com.teamtwo.nullfunding.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping("/makeProject")
    public ModelAndView makeProject(/*@RequestParam int page,*/ HttpServletRequest request, ModelAndView mv){

        /* 넘어갈 페이지처리 */
        String page = request.getParameter("page");
        String projectPage = "content/project/makePJ" + page;
        mv.setViewName(projectPage);
        
        /* 페이지 내에서 정보 저장해서 넘겨주기 */
        mv.addObject("title", request.getParameter("title"));
        mv.addObject("fundGoal", request.getParameter("fundGoal"));
        mv.addObject("description", request.getParameter("description"));


        return mv;
    }

    @RequestMapping("/projectInfo")
    public String projectInfo(){

        return "content/project/project-info";
    }

}
