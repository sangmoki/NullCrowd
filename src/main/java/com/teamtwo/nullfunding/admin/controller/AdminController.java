package com.teamtwo.nullfunding.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/preview")
    public ModelAndView adminPreview(ModelAndView mv){

        mv.setViewName("content/admin/preview");

        return mv;
    }

    @GetMapping("/member")
    public ModelAndView adminMember(ModelAndView mv){

        mv.setViewName("content/admin/member");

        return mv;
    }

    @GetMapping("/project")
    public ModelAndView adminProject(ModelAndView mv){

        mv.setViewName("content/admin/project");

        return mv;
    }

    @GetMapping("/money")
    public ModelAndView adminMoney(ModelAndView mv){

        mv.setViewName("content/admin/money");

        return mv;
    }

    @GetMapping("/hot")
    public ModelAndView adminHot(ModelAndView mv){

        mv.setViewName("content/admin/hot");

        return mv;
    }

    @GetMapping("/message")
    public ModelAndView adminMessage(ModelAndView mv){

        mv.setViewName("content/admin/message");

        return mv;
    }

    @GetMapping("/inquiry")
    public ModelAndView adminInquiry(ModelAndView mv) {

        mv.setViewName("content/admin/inquiry");

        return mv;
    }






}
