package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.common.Exception.message.MessageDeleteException;
import com.teamtwo.nullfunding.common.Exception.message.MessageSendException;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
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


    @GetMapping("/checkMessage")
    public ModelAndView messageList(HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails, @RequestParam int box_type,
                                    @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                    ModelAndView mv) {

        System.out.println("box_type = " + box_type);
        /* 불러올 대상(닉네임)과, 해당되는 메시지 범위를 특정함 */

        Map<String, Object> searchMap = new HashMap<>();
        Map<String, Integer> memberMap = new HashMap<>();
        Map<String, Integer> messageboxMap = new HashMap<>();

        memberNo = ((UserImpl) userDetails).getMemCode();
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
        mv.addObject("boxNumber", messageboxNo);


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

    /* 메시지 삭제 = DB의 'IS_DELETED' 컬럼을 N->Y로 바꿈으로써, 사용자에게만 노출되지 않게 하는 논리적 삭제 실행 */
    @RequestMapping(value = "/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String processDeleteMessageList(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr, String[] delMess) {

        for (int i = 0; i < delMess.length; i++) {
            log.info("[MessageController] 메시지 번호 " + delMess[i] + "의 삭제 대기 중..");
            messageService.deleteMessage(Integer.valueOf(delMess[i]));
            log.info("[MessageController] " + delMess[i] + "번 메시지 삭제 성공!");
//            rttr.addFlashAttribute("message", "success"); => 왜 안될까?? html이랑 연동해서 건드려봐도 안돼서 그냥 html에  $.ajax({쪽 success, error 구문 그대로 방치함...
        }
        log.info("[MessageController] 모든 메시지 삭제 완료 : 페이지를 새로고침 합니다!");
        return "redirect:/pm/checkMessage";
    }

    /* 메시지 보기 */
    @GetMapping("/readMessage")
    public String readIndividualMessage(HttpServletRequest request, @RequestParam(value = "currentMessage") int messageNo, Model model) {

        log.info("[MessageController] 읽기 요청된 메시지 번호 : " + messageNo);
        MessageDTO messageDetail = messageService.viewDetailOfSelectedMessage(messageNo);
        log.info("[MessageController] 읽기 요청된 메시지 정보 : " + messageDetail);
        model.addAttribute("message", messageDetail);

        return "content/pm/readMessage";

    }

    /* 메시지 보내기 */
    @GetMapping("/sendMessage")
    public String goSendMessage() { return "content/pm/sendMessage"; }

    /* 메시지 답장하기 */
    @GetMapping("/replyMessage")
    public String goReplyMessage() {
        return "content/pm/replyMessage";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute MessageDTO message, @RequestParam int boxType, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes rttr) throws MessageSendException {

        Map<String, Object> searchMap = new HashMap<>();
        memberNo = ((UserImpl) userDetails).getMemCode();
        log.info("[MessageController] 발신자 유저번호 : " + memberNo);
        searchMap.put("memberNo", Integer.valueOf(memberNo));
        message.setReceiverMemberNo(messageService.getMemberNoByNickname(message.getSenderNickname()));
        log.info("[MessageController] 수신자 닉네임 : " + message.getReceiverNickname());
        log.info("[MessageController] 수신자 유저번호 : " + message.getReceiverMemberNo());
        message.setBoxType(boxType);
        searchMap.put("message", message);

        log.info("[MessageController] 다음 메시지에 대한 발신 요청 확인 : " + message);
        messageService.sendMessage(searchMap);

        return "redirect:/content/pm/checkMessage";
    }

    @PostMapping("/replyMessage")
    public String replyMessage(@ModelAttribute MessageDTO message, @RequestParam String messageSender, @RequestParam String messageTitle, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes rttr) throws MessageSendException {

        Map<String, Object> searchMap = new HashMap<>();
        memberNo = ((UserImpl) userDetails).getMemCode();
        log.info("[MessageController] 발신자 유저번호 : " + memberNo);
        searchMap.put("memberNo", Integer.valueOf(memberNo));
        message.setReceiverMemberNo(messageService.getMemberNoByNickname(messageSender));
        message.setMessageTitle(messageTitle);
        log.info("[MessageController] 수신자 닉네임 : " + message.getReceiverNickname());
        log.info("[MessageController] 수신자 유저번호 : " + message.getReceiverMemberNo());
        searchMap.put("message", message);

        log.info("[MessageController] 다음 메시지에 대한 발신 요청 확인 : " + message);
        messageService.sendMessage(searchMap);

        return "redirect:/content/pm/checkMessage";
    }


    /* 닉네임 검색 = 닉네임 값을 검색해 해당하는 닉네임값을 넘겨줌 */
    @RequestMapping(value = "/searchNickname", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    public @ResponseBody String[] searchNickname(@RequestParam("nickname") String nickname) {

        String[] searchedNickname = new String[3];
        log.info("[MessageController] 다음 닉네임에 대한 검색 요청 확인 : " + nickname);
        searchedNickname = messageService.searchNicknameAndMessageboxNo(nickname);
        System.out.println("searchedNickname = " + searchedNickname[0]);
        System.out.println("searchedNickname = " + searchedNickname[1]);
        System.out.println("searchedNickname = " + searchedNickname[2]);
        return searchedNickname;
    }

}
