package com.teamtwo.nullfunding.project.controller;

import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import com.teamtwo.nullfunding.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private List<ProjectRewardDTO> rewardList;

    @Autowired
    public ProjectController(ProjectService projectService, List<ProjectRewardDTO> rewardList) {
        this.projectService = projectService;
        this.rewardList = rewardList;
    }

    @RequestMapping("/addReward")
    @ResponseBody
    public HttpServletResponse addReward
            (@ModelAttribute ProjectRewardDTO projectRewardDTO, HttpServletResponse response){

        rewardList.add(projectRewardDTO);
        return response;
    }

    @RequestMapping("/makeProject")
    public ModelAndView makeProject(HttpServletRequest request, ModelAndView mv){

        /* 넘어갈 페이지처리 */
        String page = request.getParameter("page");
        String projectPage = "content/project/makePJ" + page;
        mv.setViewName(projectPage);

//       rewardList.add("")

        /* 페이지 내에서 정보 저장해서 넘겨주기 */
        mv.addObject("title", request.getParameter("title"));
        mv.addObject("fundGoal", request.getParameter("fundGoal"));
        mv.addObject("description", request.getParameter("description"));
        mv.addObject("rewardList", rewardList);
        System.out.println("rewardList = " + rewardList);
        return mv;
    }

    @RequestMapping("/projectInfo")
    public String projectInfo(){

        return "content/project/project-info";
    }

}
