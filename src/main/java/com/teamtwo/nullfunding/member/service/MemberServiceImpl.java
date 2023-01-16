package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dao.MemberMapper;
import com.teamtwo.nullfunding.member.dto.FundRaiserDTO;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.MembershipDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MemberMapper mapper;

    private JavaMailSender mailSender;


    @Autowired
    public MemberServiceImpl(MemberMapper mapper, JavaMailSender mailSender) {

        this.mapper = mapper;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {

        /* 로그인 시 사용한 아이디를 가지고 데이터베이스에가서 사용자 정보를 조회 */
        MemberDTO member = mapper.findMemberById(memEmail);

        if (member == null) {
            member = new MemberDTO();
        }
        String authority = member.getAuthName();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        System.out.println("member = " + member);
        UserImpl user = new UserImpl(member.getMemEmail(), member.getMemPwd(), authorities);
        user.setDetails(member);
        return user;

    }


    @Override
    public int insertMember(MemberDTO member) {
        int resultMember = mapper.insertMember(member);
        int resultPersonal = mapper.insertPersonalInfo(member.getPersonalInfoDTO());
        FundRaiserDTO fundRaiserDTO = new FundRaiserDTO();
        fundRaiserDTO.setRaiEmail(member.getMemEmail());
        fundRaiserDTO.setRaiPhone(member.getPersonalInfoDTO().getPhone());
        int resultFundRaiser = mapper.insertFundRaiser(fundRaiserDTO);

        int result = 0;

        if(resultMember >= 1 || resultPersonal >= 1 || resultFundRaiser >= 1){
            result = 1;
        } else{
            result = 0;
        }
        return result;
    }

    @Override
    public int idDupCheck(String memEmail) {

        int result = mapper.idDupCheck(memEmail);

        return result;
    }

    @Override
    public int nickDupCheck(String nickName) {

        int result = mapper.nickDupCheck(nickName);

        return result;
    }

    // 멤버의 모든 멤버쉽 가져옴
    @Override
    public List<MembershipDTO> getMembershipData(int memberNo) {

        List<MembershipDTO> membershipList = new ArrayList<>();
        membershipList = mapper.getMembershipData(memberNo);
        log.info("[MemberService] 가져온 멤버십 리스트 : " + membershipList);

        return membershipList;
    }


    // 멤버쉽 적용기간 뽑기
    @Override
    public Map<String, String> getMembershipPeriod(List<MembershipDTO> membershipList) throws ParseException {

        // 결과값 리턴할 객체 생성 후 기본값 null로 설정
        Map<String, String>  membershipPeriod = new HashMap<>();
        membershipPeriod.put("startDate", null);
        membershipPeriod.put("endDate", null);


        // 매개변수로 받은 MembershipDTO형식의 List에서 멤버십 시작날짜와 끝날짜를 하나씩 꺼내 비교할 것

        // 1. 리스트에 몇개의 멤버십이 들었는지 아직 모르니 for문을 돌릴 준비
        for(MembershipDTO data : membershipList){

            // 1. 유효성 검사 대상 날짜 변수 지정(startDate, endDate) 및 매개변수로 리스트에서 값을 꺼내 담음
            log.info("[MemberService] "+ data + "에 대한 유효성 검사 시작");
            Date startDate = data.getStartDate();
            log.info("[MemberService] 검사 대상 멤버십 시작일 : " + startDate);
            Date endDate = data.getEndDate();
            log.info("[MemberService] 검사 대상 멤버십 종료일 : " + endDate);

            // 2. 오늘 날짜와 멤버십의 startDate 비교
            boolean startDateIsValid = false;
            // 시작날짜.compareTo(오늘날짜) 비교해서 시작날짜가 작으면 startDateIsValid는 true
            if(startDate.compareTo(new Date())<=0){
                log.info("[MemberService] 시작일과 오늘 비교결과 : "+(startDate.compareTo(new Date())<=0));
                startDateIsValid = true;
            } else { startDateIsValid = false; }

            // 3. 오늘 날짜와 멤버십의 endDate 비교
            boolean endDateIsValid = false;
            // 끝날짜.compareTo(오늘날짜) 비교해서 끝날짜가 크면 endDateIsValid는 true
            if(endDate.compareTo(new Date())>=0){
                log.info("[MemberService] 종료일과 오늘 비교결과 : "+(endDate.compareTo(new Date())>=0));
                endDateIsValid = true;
            } else { endDateIsValid = false; }

            // 4. 위의 두 날짜가 true인지 + 해당 멤버십이 환불신청된 건 아닌지까지 if문에 넣고
            log.info("[MemberService] 검사 대상 멤버십 유효 여부 : " + data.isValid());
            if(startDateIsValid && endDateIsValid && data.isValid()){
                // 5. 셋 모두 참일 경우, 해당하는 startDate와 endDate를 생성해둔 맵 객체에 넣고 반환
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
                membershipPeriod.put("startDate", simpleDateFormat.format(startDate));
                membershipPeriod.put("endDate", simpleDateFormat.format(endDate));
                log.info("[MemberService] 현재 적용 중인 멤버십 시작일 : "+ simpleDateFormat.format(startDate));
                log.info("[MemberService] 현재 적용 중인 멤버십 종료일 : "+simpleDateFormat.format(endDate));

                // 하나라도 true&true&true로 걸리면, 해당 startDate, endDate 값들을 해시맵에 담고 리턴
                return membershipPeriod;
            }

        }

        // 리스트에 반복문을 다 돌려도 없으면 null, null 객체를 돌려줌
        return membershipPeriod;
    }

    // 누적 멤버쉽 개월수 계산해 뽑아옴 (* 누적 멤버십 = 환불되지 않은 멤버십 구매이력)
    @Override
    public int getMembershipMonth(List<MembershipDTO> membershipList) {

        // 결과값 리턴할 객체 생성 후 기본값 0으로 설정
        int membershipMonth = 0;

        // 1. 리스트에 몇개의 멤버십이 들었는지 아직 모르니 for문을 돌릴 준비
        for(MembershipDTO data : membershipList){

            // 2. isValid가 true일때마다 membershipMonth가 1씩 증가
            if(data.isValid()){
                membershipMonth = membershipMonth +1;
            }

        }

        // 반복문 다 돌렸으면 반환
        return membershipMonth;
    }



//    // 누적 후원금액 계산 : 총 결제액 - 멤버십 구매액
//    @Override
//    public int getFundSupport(MembershipDTO memberNo) {
//        return 0;
//    }


}
