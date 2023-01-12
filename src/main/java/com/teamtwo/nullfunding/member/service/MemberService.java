package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.MembershipDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Member;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface MemberService extends UserDetailsService {


    int insertMember(MemberDTO member);

    int idDupCheck(String memEmail);

    int nickDupCheck(String nickName);

    // 멤버의 모든 멤버쉽 가져옴 (service단 계산용 데이터풀)
    List<MembershipDTO> getMembershipData(int memberNo);

    // 멤버쉽 적용기간 뽑아옴 (service단 계산용 메소드)
    Map<String, String> getMembershipPeriod(List<MembershipDTO> membershipList) throws ParseException;

    // 멤버쉽 개월수 계산 : 환불되지 않은 멤버십 구매횟수 (service단 계산용 메소드)
    int getMembershipMonth(List<MembershipDTO> membershipList);


//    //누적 후원금액 계산 : 총 결제액 - 멤버십 구매액
//    int getFundSupport(MembershipDTO memberNo);




}
