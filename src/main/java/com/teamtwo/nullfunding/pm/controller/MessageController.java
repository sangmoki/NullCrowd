package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.service.MessageService;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pm")
public class MessageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MessageService messageService;

    private int memberNo;
    private int messageboxNo;

    private String nickname;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping ("/checkMessage")
    public ModelAndView messageList(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails, @RequestParam int box_type,
                                    @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                    ModelAndView mv){

        System.out.println("box_type = " + box_type);
        /* 불러올 대상(닉네임)과, 해당되는 메시지 범위를 특정함 */

        Map<String, Object> searchMap = new HashMap<>();
        Map<String, Integer> memberMap = new HashMap<>();
        Map<String, Integer> messageboxMap = new HashMap<>();

        memberNo = ((UserImpl)userDetails).getMemCode();
        log.info("[MessageController] 현재 특정된 유저번호 : " + memberNo);
        searchMap.put("memberNo", Integer.valueOf(memberNo));

        messageboxNo = box_type;
        log.info("[MessageController] 현재 특정된 메시지박스 번호 : " + messageboxNo);
        searchMap.put("messageboxNo", Integer.valueOf(messageboxNo));

        log.info(String.valueOf(searchMap.get("memberNo")));
        log.info(String.valueOf(searchMap.get("messageboxNo")));



        /* 조회 대상인 닉네임을 가져올 것 */
        nickname = messageService.checkObjectNickname(searchMap);
        log.info("[MessageController] 현재 특정된 대상 닉네임 : " + nickname);
        mv.addObject("nickname", nickname);


        /* 조회 대상인 전체 메시지 수를 가져올 것 */
//        searchMap.put("nickname", nickname);
        int totalCount = messageService.checkTotalMessages(searchMap);
        log.info("[MessageController] 특정된 대상 닉네임이 가진 메시지 수 : " + totalCount);


        /* 페이징 처리 관련 설정 */
        int limit = 10;             // 한페이지에 보여줄 메시지 수
        int buttonAmount = 5;       // 한번에 보여질 페이징 버튼 수


        /* 페이징 처리를 해서 관련 정보를 담고 있는 인스턴스를 반환 */
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        /* 인스턴스와 박스타입을 맵으로 처리 */
        Map<String, Object> selectCriteriaPlusAlpha = new HashMap<>();
        selectCriteriaPlusAlpha.put("selectCriteria", selectCriteria);
        selectCriteriaPlusAlpha.put("memberNo", memberNo);
        selectCriteriaPlusAlpha.put("messageboxNo", messageboxNo);

        /* 조회 후 처리 */
        List<MessageDTO> messageList = messageService.viewAllMessageList(selectCriteriaPlusAlpha);
        log.info("[MessageController] 메시지 리스트 : " + messageList);

        mv.addObject("messageList", messageList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[MessageController] 지정된 검색조건 : " + selectCriteria);
        mv.setViewName("content/pm/checkMessage");

        return mv;
    }


    @GetMapping("/sendMessage")
    public String sendMessage(){
        return "content/pm/sendMessage";
    }

//    @PostMapping("/sendMessage")
//    public void sendMessage(@ModelAttribute MessageDTO message, RedirectAttributes rttr,
//                              @AuthenticationPrincipal UserDetails userDetails) {
//
//        log.info("[MessageController] sendMessage : " + message);
//        messageService.sendMessage(message);
//        rttr.addFlashAttribute("message", "메시지 보내기에 성공하셨습니다.");
//    }
}
