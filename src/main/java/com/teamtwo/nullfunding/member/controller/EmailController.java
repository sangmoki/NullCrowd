package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send")
public class EmailController {

    @Autowired
    private MemberService memberService;

    @GetMapping({"/"})
    public void defaultLoaction(){}



    @GetMapping("/mail")
    public String sendMail(){

        String randomCode = memberService.getRandomCode();
        // 랜덤 난수 생성 메소드를 불러 String 에 담음.
        System.out.println("RandomCode = " + randomCode);               // 만들어진 랜덤 난수 확인. -> DB에 담아야함 (확인용)

        memberService.sendEmail("bs11mailsender@gmail.com",
                "회원가입 인증 메일입니다.",
                randomCode,
                "위의 인증번호를 인증창에 입력해주세요.");
        return "redirect:/";
    }
}
