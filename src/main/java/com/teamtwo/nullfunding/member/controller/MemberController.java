package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {


    @GetMapping("/signup")
    public String signupMember(){

        return "content/member/signup";
    }

    @GetMapping("/login")
    public ModelAndView goLogIn(ModelAndView mv){

        mv.setViewName("content/member/login");

        return mv;
    }
}

