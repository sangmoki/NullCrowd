//package com.teamtwo.nullfunding.payment.controller;
//
//import com.teamtwo.nullfunding.member.dto.UserImpl;
//import com.teamtwo.nullfunding.payment.service.PaymentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/payment")
//public class PaymentController {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private PaymentService paymentService;
//    private String content;
//    private int quantity;
//
//    public PaymentController(PaymentService paymentService){ this.paymentService = paymentService; }
//
//    @GetMapping("/confirmAndPay")
//    public ModelAndView confirmAndPay(@RequestParam("content") String content, @RequestParam("quantity") int quantity,
//                                      @AuthenticationPrincipal UserDetails userDetails, ModelAndView mv){
//
//        // 구매자 회원정보(회원번호)는 현재 로그인 세션에서 뽑아와서 담는다.
//        int memberNo = ((UserImpl) userDetails).getMemCode();
//        mv.addObject("memberNo", memberNo);
//        log.info("[MemberController] 구매자 회원번호 : " + memberNo);
//
//        // 사용자가 구매하기로 한 기초 결제 정보는 파라미터로 받는다 (품목명, 수량)
//        mv.addObject("content", content);
//        mv.addObject("quantity", quantity);
//
//        // 보낼 곳 설정 후 리턴
//        mv.setViewName("content/payment/confirmAndPay");
//
//        return mv;
//
//    }
//
////    @PostMapping("/confirmAndPay")
////    public ModelAndView confirmAndPay(){
////
////    }
//
//
//
//
//
//}
