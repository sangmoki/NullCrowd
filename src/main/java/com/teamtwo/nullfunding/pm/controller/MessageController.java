package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Value("src/main/resources")
    private String IMAGE_DIR;
    private MessageService messageService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MessageController(PasswordEncoder passwordEncoder, MemberService memberService) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
    }

    @GetMapping("/signup")
    public String signupMember(){

        return "content/member/signup";
    }

    @GetMapping("/login")
    public String goLogIn(){
        return "content/member/login";
    }

    @GetMapping("myPage")
    public String goMyPage(){

        return "content/member/myPage";
    }
}
