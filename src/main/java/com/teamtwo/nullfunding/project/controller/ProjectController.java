package com.teamtwo.nullfunding.project.controller;

import com.teamtwo.nullfunding.project.model.dto.ProjectMediaDTO;
import com.teamtwo.nullfunding.project.model.dto.ProjectRewardDTO;
import com.teamtwo.nullfunding.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Value("src/main/resources/static/img/projectImg")
    private String IMAGE_DIR;

    private ProjectService projectService;
    private List<ProjectRewardDTO> rewardList;

    @Autowired
    public ProjectController(ProjectService projectService, List<ProjectRewardDTO> rewardList) {
        this.projectService = projectService;
        this.rewardList = rewardList;
    }

    @RequestMapping("/addReward")
    @ResponseBody
    public void addReward(@ModelAttribute ProjectRewardDTO projectRewardDTO, HttpServletRequest request) {


        rewardList.add(projectRewardDTO);
        System.out.println("rewardList = " + rewardList);
        request.setAttribute("rewardList", rewardList);

    }

    @RequestMapping("/makeProject")
    public ModelAndView makeProject(HttpServletRequest request, ModelAndView mv) {

        /* 넘어갈 페이지처리 */
        String page = request.getParameter("page");
        String projectPage = "content/project/makePJ" + page;
        mv.setViewName(projectPage);
//       rewardList.add("")

        /* 페이지 내에서 정보 저장해서 넘겨주기 */
        // PJ1
        mv.addObject("title", request.getParameter("title"));
        mv.addObject("fundGoal", request.getParameter("fundGoal"));
        mv.addObject("description", request.getParameter("description"));
        mv.addObject("startDate", request.getParameter("startDate"));
        mv.addObject("endDate", request.getParameter("endDate"));
        mv.addObject("videoURL", request.getParameter("videoURL"));
        // PJ2
        mv.addObject("rewardList", rewardList);
        // PJ3
        mv.addObject("refundRule", request.getParameter("refundRule"));
        mv.addObject("tel", request.getParameter("tel"));
        mv.addObject("pjEmail", request.getParameter("pjEmail"));
        return mv;
    }

    @RequestMapping("/projectInfo")
    public String projectInfo() {

        return "content/project/project-info";
    }

    @RequestMapping("/removeReward")
    @ResponseBody
    public void modifyReward(@RequestParam int index) {
        rewardList.remove(index);
    }

    @PostMapping("addMaiImg")
    @ResponseBody
    public void addMaiImg(@ModelAttribute ProjectMediaDTO  projectMediaDTO, @RequestParam("file") MultipartFile mainImg, HttpServletRequest request) {

        String message = "";
        System.out.println("projectMediaDTO = " + projectMediaDTO);

        String rootLocation = IMAGE_DIR;

        String fileUploadDirectory = rootLocation;

//        File directory = new File(fileUploadDirectory);
//
//        System.out.println("directory = " + directory);

//        if (!directory.exists()) {
//
//            System.out.println("directory생성 = " + directory.mkdirs());
//        }

        System.out.println("mainImg = " + mainImg);
        try {
            if (mainImg.getSize() > 0) {
                String originFileName = mainImg.getOriginalFilename();

                System.out.println("originFileName = " + originFileName);

                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedFileName = UUID.randomUUID().toString().replace("-", "") + ext;

                System.out.println("savedFileName = " + savedFileName);


                mainImg.transferTo((new File(fileUploadDirectory + "/" + savedFileName)));

                String url = fileUploadDirectory + "/" + savedFileName;
                projectMediaDTO.setFileName(originFileName);
                projectMediaDTO.setHashName(savedFileName);
                projectMediaDTO.setMediaType("image");
                projectMediaDTO.setUrl(url);
            }
        } catch (IOException e) {

            File deleteFile = new File(projectMediaDTO.getUrl());

            if(deleteFile.delete()){
                System.out.println("업로드에 실패한 사진 삭제 완료!");
            } else {
                e.printStackTrace();
            }

            throw new RuntimeException(e);
        }
    }

}

