package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.service.MessageService;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping ("/checkMessage")
    public ModelAndView messageList(HttpServletRequest request, @RequestParam int selectedMemberNo, @RequestParam int selectedMessageboxNo,
                                    @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                    ModelAndView mv){

        /* 불러올 대상 메시지 범위를 특정함 */
        Map<String, Integer> searchMap = new HashMap<>();

        memberNo = selectedMemberNo;
        log.info("[MessageController] 현재 특정된 유저번호 : " + selectedMemberNo);
        searchMap.put("selectedMemberNo", Integer.valueOf(memberNo));

        messageboxNo = selectedMessageboxNo;
        log.info("[MessageController] 현재 특정된 메시지박스 번호 : " + selectedMessageboxNo);
        searchMap.put("selectedMessageboxNo", Integer.valueOf(messageboxNo));



        /* 조회 대상인 전체 메시지 수를 가져올 것 */
        int totalCount = messageService.checkTotalMessages(searchMap);


        /* 페이징 처리 관련 설정 */
        int limit = 10;             // 한페이지에 보여줄 메시지 수
        int buttonAmount = 5;       // 한번에 보여질 페이징 버튼 수


        /* 페이징 처리를 해서 관련 정보를 담고 있는 인스턴스를 반환 */
        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        /* 조회 후 처리 */
        List<MessageDTO> messageList = messageService.viewAllMessageList(selectCriteria);
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
