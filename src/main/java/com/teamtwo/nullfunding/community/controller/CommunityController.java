package com.teamtwo.nullfunding.community.controller;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.service.CommunityServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CommunityServiceImpl communityServiceImpl;

    public CommunityController(CommunityServiceImpl communityServiceImpl) {
        this.communityServiceImpl = communityServiceImpl;
    }

    @GetMapping("/list")
    public ModelAndView communityList(ModelAndView mv) {

        mv.setViewName("content/board/community");

        return mv;
    }

}