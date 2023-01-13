package com.teamtwo.nullfunding.admin.controller;

import com.teamtwo.nullfunding.admin.service.AdminMemberService;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProjectController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AdminMemberService adminMemberService;

    @Autowired
    public AdminProjectController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    /* 프로젝트 관리 부분 */
    @GetMapping("/project")
    public String adminProject(Model model){
        List<ProjectDTO> projects = adminMemberService.selectAllProject();
        model.addAttribute("projects", projects);

        return "/content/admin/project";
    }

    @PostMapping("/confirmProject")
    @ResponseBody
    public void confirmProject(@RequestParam int projectNo, @RequestParam int decision){

        adminMemberService.confirmProject(projectNo, decision);



    }
}
