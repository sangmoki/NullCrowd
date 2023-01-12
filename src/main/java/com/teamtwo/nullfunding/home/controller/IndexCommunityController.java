package com.teamtwo.nullfunding.home.controller;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.home.dto.IndexCommunityDTO;
import com.teamtwo.nullfunding.home.service.IndexCommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/indexCommunity")
public class IndexCommunityController {

    IndexCommunityService indexCommunityService;

    @Autowired
    public IndexCommunityController(IndexCommunityService indexCommunityService) {
        this.indexCommunityService = indexCommunityService;
    }

    @GetMapping("/list")
    public ModelAndView indexCommunity(ModelAndView mv) {


        List<IndexCommunityDTO> indexCommunityList = indexCommunityService.indexCommunity();
        mv.addObject("indexCommunityList", indexCommunityList);


        mv.setViewName("/content/index");

        return mv;
    }
}
