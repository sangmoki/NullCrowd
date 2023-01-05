package com.teamtwo.nullfunding.community.controller;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 모든 게시판 조회
    @GetMapping("/list")
    public ModelAndView communityList(ModelAndView mv) {

        List<CommunityDTO> communityList = communityService.selectAllCommunityList();
        mv.addObject("communityList", communityList);

        mv.setViewName("content/community/communityList");

        return mv;
    }
}