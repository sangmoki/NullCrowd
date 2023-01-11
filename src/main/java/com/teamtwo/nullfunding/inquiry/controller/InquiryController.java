package com.teamtwo.nullfunding.inquiry.controller;

import com.teamtwo.nullfunding.inquiry.model.dto.InquiryDTO;
import com.teamtwo.nullfunding.inquiry.service.InquiryService;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.SourceVersion;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/list")
    public ModelAndView inquiryList(ModelAndView mv){


        mv.setViewName("content/inquiry/question");

        return mv;
    }

    @GetMapping("/request")
    public ModelAndView goInquiry(ModelAndView mv){

        mv.setViewName("content/inquiry/request");

        return mv;
    }

    /* 문의 정보 담아서 관리자에게 보내기 */
    @PostMapping("/request")
    public String inquiryRequest(@ModelAttribute InquiryDTO inquiry
                                ,@AuthenticationPrincipal UserDetails userDetails) {

        int memCode = ((UserImpl) userDetails).getMemCode();
        inquiry.setMemCode(memCode);

        System.out.println("inquiry = " + inquiry);
        inquiryService.insertInquiry(inquiry);

        return "redirect:/inquiry/list";
    }
}
