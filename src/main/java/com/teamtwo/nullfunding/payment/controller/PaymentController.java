package com.teamtwo.nullfunding.payment.controller;

import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.payment.dto.PaymentDTO;
import com.teamtwo.nullfunding.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private PaymentService paymentService;
    private String content;
    private int quantity;

    @Autowired
    public PaymentController(PaymentService paymentService){ this.paymentService = paymentService; }

    @GetMapping("/confirmAndPay")
    public ModelAndView confirmAndPay(@RequestParam("content") String content, @RequestParam("quantity") int quantity,
                                      @AuthenticationPrincipal UserDetails userDetails, ModelAndView mv){

        // 구매자 회원정보(회원번호)는 현재 로그인 세션에서 뽑아와서 담는다.
        int memberNo = ((UserImpl) userDetails).getMemCode();
        mv.addObject("memberNo", memberNo);
        log.info("[MemberController] 구매자 회원번호 : " + memberNo);

        // 사용자가 구매하기로 한 기초 결제 정보는 파라미터로 받는다 (품목명, 수량)
        mv.addObject("content", content);
        log.info("[MemberController] 구매 품목 : " + content);
        mv.addObject("quantity", quantity);
        log.info("[MemberController] 구매 수량 : " + quantity);

        // 사용자가 구매하기로 한 구매정보가 '멤버십'일 경우, 구매 시 적용기간도 넘긴다
        if(content.equals("membership")){

            log.info("[MemberController] 구매 품목이 membership이면 진행되는 코드 호출됨.");
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 30);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시");

            // 멤버십 시작일은 현재(구매 진행)시각
            mv.addObject("startDate", simpleDateFormat.format(date));
            log.info("[MemberController] 구매 시 membership 시작일 " + simpleDateFormat.format(date));

            // 종료일은 현재날짜 +30일 뒤
            mv.addObject("endDate", simpleDateFormat.format(cal.getTime()));
            log.info("[MemberController] 구매 시 membership 종료일 " + simpleDateFormat.format(cal.getTime()));

        }

        // 보낼 곳 설정 후 리턴
        mv.setViewName("content/payment/confirmAndPay");

        return mv;

    }

//    @PostMapping("/doPay")
//    public Model confirmAndPay(@ModelAttribute PaymentDTO payment, Model model){
//
//
//
//    }


//    @PostMapping("/confirmAndPay")
//    public ModelAndView confirmAndPay(){
//
//    }





}
