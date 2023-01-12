package com.teamtwo.nullfunding.member.controller;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.MembershipDTO;
import com.teamtwo.nullfunding.member.dto.PersonalInfoDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/member")
public class MemberController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final PasswordEncoder passwordEncoder;
    private MemberService memberService;
    private EmailController emailController;
    private int nickResult;

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



    /* 시작 :: 멤버십 - 결제 관련 부분입니다 */

    // 멤버십 확인 처리 : 사용자가 마이페이지에서 '멤버십'버튼 눌렀을 시 -> 현재 멤버십 현황을 뽑아서 출력
    @GetMapping("/membership")
    public ModelAndView goMembership(@AuthenticationPrincipal UserDetails userDetails, ModelAndView mv) throws ParseException {

        // 대상 회원정보(회원번호)는 현재 로그인 세션에서 뽑아와서 담는다.
        int memberNo = ((UserImpl) userDetails).getMemCode();
        mv.addObject("memberNo", memberNo);
        log.info("[MemberController] 멤버십 조회 대상 회원번호 : " + memberNo);

        /* 대상 회원의 멤버십 정보는 DB에서 가져와 담는다. */

        // 1. 우선 멤버의 모든 멤버십을 가져온다
        List<MembershipDTO> membershipList = memberService.getMembershipData(memberNo);
        log.info("[MemberController] 넘겨받은 멤버십 리스트 : " + membershipList);

        // 2. 가져온 멤버십으로 멤버십 적용기간을 뽑는다.
        Map<String, String> membershipPeriod = memberService.getMembershipPeriod(membershipList);
        System.out.println("리턴받은 membershipPeriod (결과값) ============> " + membershipPeriod);

        // 2-1. 가져온 멤버십 적용기간 시작날짜, 종료날짜 모두 null이 아니면, 유효한 날짜를 보내고 아니면 모두 null로 세팅해 보낸다.
        if(membershipPeriod.get("startDate")!=null && membershipPeriod.get("endDate")!=null){
            mv.addObject("startDate", membershipPeriod.get("startDate"));
            mv.addObject("endDate", membershipPeriod.get("endDate"));

        // 2-2. 시작날짜, 종료날짜 하나라도 null일 경우, 두 값 모두 null 처리해서 보낸다.
        } else {
            mv.addObject("startDate", null);
            mv.addObject("endDate", null);
        }

        log.info("[MemberController] 현재 적용 중인 멤버십 시작일자 : " + membershipPeriod.get("startDate"));
        log.info("[MemberController] 현재 적용 중인 멤버십 종료일자 : " + membershipPeriod.get("endDate"));


        // 3. 누적 멤버십 개월수(환불하지 않은, 유효한 구매내역)를 뽑아서 담는다.
        int membershipMonth = memberService.getMembershipMonth(membershipList);
        mv.addObject("membershipMonth", membershipMonth);
        log.info("[MemberController] 누적 멤버십 개월 수 : " + membershipPeriod.get("endDate"));

        // 담아서 어디로 보낼지 정하고, 보낸다.
        mv.setViewName("content/member/membership");

        return mv;

    }

    // 멤버십 구매 처리 : 사용자가 '멤버십 구매'버튼 눌렀을 시 -> 결제확인 및 결제처리 관련 페이지로 넘김
    @PostMapping("/getMembership")
    public ModelAndView getMembership(@AuthenticationPrincipal UserDetails userDetails, ModelAndView mv,
                                    @RequestParam("objectName") String name, @RequestParam("amount") int amount){

        // 대상 회원정보(회원번호)는 현재 로그인 세션에서 뽑아와서 담는다.
        int memberNo = ((UserImpl) userDetails).getMemCode();
        mv.addObject("memberNo", memberNo);

        // 뭘 구매하는지(구매 페이지에서 알아야 하니까 파라미터로 받아서 Payment 쪽으로 넘겨준다.)
        // -> 당장은 품목:멤버십, 수량:1개 지만, payment 처리 방식을 일원화 할걸 고려해서 뻔한 값이라도 그냥 넘겨주는 방식으로..
        mv.addObject("name", name);
        mv.addObject("amount", amount);

        // 구매 쪽으로 넘겨야 하니까, 구매로 보낸다.
        mv.setViewName("content/payment/confirmAndPay");

        return mv;
    }

    /* 끝 :: 멤버십 - 결제 관련 부분입니다 */

}

