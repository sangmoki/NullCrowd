package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.common.Exception.member.MemberInsertException;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/member")
public class MemberController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncoder passwordEncoder;
    private MemberService memberService;
    private EmailController emailController;

    public MemberController(PasswordEncoder passwordEncoder, MemberService memberService, EmailController emailController) {
        this.passwordEncoder = passwordEncoder;
        this.memberService = memberService;
        this.emailController = emailController;
    }

    @GetMapping("/signup")
    public String goSignup(){

        return "content/member/signup";
    }

    @PostMapping("/signup")
    public String insertMember(@ModelAttribute MemberDTO member, HttpServletRequest request,
                               RedirectAttributes rttr) throws MemberInsertException {

        log.info("");
        log.info("");
        log.info("[MemberController] registMember ================================================================");

        member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));

        log.info("[MemberController] insertMember request Member : " + member);

        memberService.insertMember(member);

        rttr.addFlashAttribute("message", "회원 가입에 성공하였습니다.");

        log.info("[MemberController] insertMember ==========================================================");

        return "redirect:/";
    }


    @GetMapping("/login")
    public String goLogIn(){
        return "content/member/login";
    }

    @GetMapping("myPage")
    public String goMyPage(){

        return "content/member/myPage";
    }

    @PostMapping("/idDupCheck")
    @ResponseBody
    public Map idDupCheck(@RequestParam("memEmail") String memEmail) {

        int result = memberService.idDupCheck(memEmail);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("result", result);
        if(result == 0){

         String randomCode =  emailController.sendEmail(memEmail);
         resultMap.put("randomCode", randomCode);
        }

        return resultMap;
    }

    @PostMapping("/nickDupCheck")
    @ResponseBody
    public int nickDupCheck(@RequestParam("nickName") String nickName){

        int nickResult = memberService.nickDupCheck(nickName);

        return nickResult;
    }
}

