package com.teamtwo.nullfunding.home.controller;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.home.service.IndexCommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/indexCommunity")
public class IndexCommunityController {

    @GetMapping("/list")
    public ModelAndView indexCommunity(ModelAndView mv) {


        List<CommunityDTO> indexCommunityList = IndexCommunityService();
        mv.addObject("indexCommunityList", indexCommunityList);


        mv.setViewName("/content/index");

        return mv;
    }
}
