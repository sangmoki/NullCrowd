//package com.teamtwo.nullfunding.memberCard.controller;
//
//import com.teamtwo.nullfunding.common.paging.Pagenation;
//import com.teamtwo.nullfunding.common.paging.SelectCriteria;
//import com.teamtwo.nullfunding.member.dto.UserImpl;
//import com.teamtwo.nullfunding.memberCard.service.MembercardService;
//import com.teamtwo.nullfunding.pm.dto.MessageDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/memberCard")
//public class MembercardController {
//
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final MembercardService membercardService;
//
//    private int memberNo;
//
//    private String nickname;
//
////    public MembercardController(MembercardService membercardService) {
////        this.membercardService = membercardService;
////    }
//
//    @GetMapping("/view")
//    public ModelAndView memberCard(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String nickname, ModelAndView mv) {
//
//        System.out.println("nickname = " + nickname);
//
//        Map<String, Object> searchMap = new HashMap<>();
//        Map<String, Integer> memberMap = new HashMap<>();
//        Map<String, Integer> messageboxMap = new HashMap<>();
//
//        /* 불러올 대상(닉네임)과, 해당되는 메시지 범위를 특정함 */
//        memberNo = ((UserImpl) userDetails).getMemCode();
//        log.info("[MessageController] 현재 특정된 유저번호 : " + memberNo);
//        searchMap.put("memberNo", Integer.valueOf(memberNo));
//
//        log.info(String.valueOf(searchMap.get("memberNo")));
//        log.info(String.valueOf(searchMap.get("messageboxNo")));
//
//
//
//        /* 조회 대상인 닉네임을 가져올 것 */
//        nickname = messageService.checkObjectNickname(searchMap);
//        log.info("[MessageController] 현재 특정된 대상 닉네임 : " + nickname);
//        mv.addObject("nickname", nickname);
//        mv.addObject("boxNumber", messageboxNo);
//
//
//        /* 조회 대상인 전체 메시지 수를 가져올 것 */
////        searchMap.put("nickname", nickname);
//        int totalCount = messageService.checkTotalMessages(searchMap);
//        log.info("[MessageController] 특정된 대상 닉네임이 가진 메시지 수 : " + totalCount);
//
//
//        /* 페이징 처리 관련 설정 */
//        int limit = 10;             // 한페이지에 보여줄 메시지 수
//        int buttonAmount = 5;       // 한번에 보여질 페이징 버튼 수
//
//
//        /* 페이징 처리를 해서 관련 정보를 담고 있는 인스턴스를 반환 */
//        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
//
//        /* 인스턴스와 박스타입을 맵으로 처리 */
//        Map<String, Object> selectCriteriaPlusAlpha = new HashMap<>();
//        selectCriteriaPlusAlpha.put("selectCriteria", selectCriteria);
//        selectCriteriaPlusAlpha.put("memberNo", memberNo);
//        selectCriteriaPlusAlpha.put("messageboxNo", messageboxNo);
//
//        /* 조회 후 처리 */
//        List<MessageDTO> messageList = messageService.viewAllMessageList(selectCriteriaPlusAlpha);
//        log.info("[MessageController] 메시지 리스트 : " + messageList);
//
//        mv.addObject("messageList", messageList);
//        mv.addObject("selectCriteria", selectCriteria);
//        log.info("[MessageController] 지정된 검색조건 : " + selectCriteria);
//        mv.setViewName("content/pm/checkMessage");
//
//        return mv;
//
//
//
//
//    }
//
//
//}
