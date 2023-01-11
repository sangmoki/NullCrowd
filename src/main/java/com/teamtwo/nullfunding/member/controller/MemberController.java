package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.PersonalInfoDTO;
import com.teamtwo.nullfunding.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String signup(@ModelAttribute MemberDTO member, @ModelAttribute PersonalInfoDTO personalInfoDTO
                        , RedirectAttributes rttr) throws ParseException {
        member.setMemPwd(passwordEncoder.encode(member.getMemPwd()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        member.setPersonalInfoDTO(personalInfoDTO);

        int result = memberService.insertMember(member);
        if(result == 1){
            rttr.addFlashAttribute("message", "등록에 성공하셨습니다!");
        } else{
            rttr.addFlashAttribute("message", "등록에 실패했습니다.");
        }

        return "redirect:/member/login";
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

