package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.common.Exception.message.MessageSendException;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.dto.MessageSelectCriteria;
import com.teamtwo.nullfunding.pm.dto.MessagePagenation;
import com.teamtwo.nullfunding.pm.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
@Controller
@RequestMapping("/pm")
public class MessageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MessageService messageService;

    // 회원번호를 담을 변수
    private int memberNo;

    /* 한 회원(회원번호)이 2가지의 역할(서포터, 프로젝트매니저)과 각 역할마다 개별 닉네임(서포터닉테임, 프로젝트매니저닉네임)을 가지므로
       -> 1. 어떤 역할에서 메시지 기능을 호출했는지를 알기 위해, 호출한 닉네임을 가져와 담을 변수 */
    private String objectNickname;

    /* 한 회원(회원번호)이 2가지의 역할(서포터, 프로젝트매니저)과 각 역할마다 개별 닉네임(서포터닉테임, 프로젝트매니저닉네임)을 가지므로
       -> 2. 해당하는 닉네임이 '서포터유저'이면 messageBox 번호는 1번, '프로젝트매니저'이면 messageBox 번호는 2번 을 가져와 담을 변수 */
    private int messageboxNo;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    /* 메시지함(메시지 리스트)을 불러온 후 뷰로 돌려주는 메소드 */
    @GetMapping("/checkMessage")
    public ModelAndView messageList(@AuthenticationPrincipal UserDetails userDetails, @RequestParam int box_type,
                                    @RequestParam(value = "currentPage", defaultValue = "1") int pageNo,
                                    ModelAndView mv) {

        /* 메시지함 번호(box_type, 쿼리스트링 값)가 제대로 받아졌는지 출력해서 확인  */
        log.info("box_type = " + box_type);

        /* 검색 정보를 담을 객체 생성 */
        Map<String, Object> searchMap = new HashMap<>();


        /* 불러올 대상(닉네임)과, 해당되는 메시지 범위를 특정하기 위한 코드 */

        // 1.회원번호(memberNo)는 접속 세션의 mainDTO인 UserImpl에서 가져와, 변수와 검색조건(searchMap)에 담고
        memberNo = ((UserImpl) userDetails).getMemCode();
        searchMap.put("memberNo", Integer.valueOf(memberNo));

        // 2. 메시지함번호(messageboxNo)는 파라미터에서 가져와, 변수와 검색조건(searchMap)에 담기
        messageboxNo = box_type;
        searchMap.put("messageboxNo", Integer.valueOf(messageboxNo));

        // 3. 검색조건(searchMap)에 검색조건 2가지가 잘 담겼는지 출력해서 확인
        log.info("[MessageController] 현재 특정된 유저번호 : " + String.valueOf(searchMap.get("memberNo")));
        log.info("[MessageController] 현재 특정된 메시지박스 번호 : " + String.valueOf(searchMap.get("messageboxNo")));


        /* '회원번호 +메시지박스 번호'를 검색조건으로 해서, 조회 대상인 '닉네임'을 특정하는 코드 */

        // 1. 대상 닉네임은 checkObjectNickname 메소드를 검색조건(searchMap)을 담아 호출하고 잘 가져와졌는지 출력해서 확인
        objectNickname = messageService.checkObjectNickname(searchMap);
        log.info("[MessageController] 현재 특정된 대상 닉네임 : " + objectNickname);

        // 2. 닉네임과 메시지함 번호를 view화면에서 보여주는 데에 활용하기 위해 ModelAndView 객체인 mv에 담음
        mv.addObject("nickname", objectNickname);
        mv.addObject("boxNumber", messageboxNo);


        /* 메시지함에 메시지를 출력하기 위한 코드 */

        // 1-1. 페이징 처리를 하기 위해 '전체 메시지 수'를 가져오고, 출력해서 확인
        int totalCount = messageService.checkTotalMessages(searchMap);
        log.info("[MessageController] 특정된 대상 닉네임이 가진 메시지 수 : " + totalCount);

        // 1-2. 페이징 처리 관련 설정
        int limit = 10;             // 한페이지에 보여줄 메시지 수
        int buttonAmount = 5;       // 한번에 보여질 페이징 버튼 수

        // 2. 페이징 처리를 위해, 조건(페이지넘버, 전체 메시지 수, 한번에 보여줄 메시지 수, 페이징 버튼 수)을 매개변수로 페이지네이션 정보 및 메소드를 활용할 인스턴스를 생성
        MessageSelectCriteria messageSelectCriteria = MessagePagenation.getSelectCriteria(memberNo, messageboxNo, pageNo, totalCount, limit, buttonAmount);

        /* 아래와 같이 하니까 페이징 처리가 제대로 안돼서, MessagePagenation.java와 MessageSelectcriteria를 새로 생성. */
//        // 3-1. 메인 DTO인 UserImpl과, 메시지처리를 위한 DTO인 MessageDTO가 달라, 두 DTO를 통합해 담기 위한 selectCriteriaPlusAlpha 객체를 생성
//        Map<String, Object> selectCriteriaPlusAlpha = new HashMap<>();
//        // 3-2. 페이징 처리를 위한 조건인 selectCriteria를 selectCriteriaPlusAlpha 객체에 담아줌
//        selectCriteriaPlusAlpha.put("selectCriteria", selectCriteria);\
//        // 3-3. 불러올 대상을 특정하기 위한 유저정보(회원번호+메시지함번호)를 selectCriteriaPlusAlpha 객체에 담아줌 (복합키 느낌으로 활용)
//        selectCriteriaPlusAlpha.setMemberNo(memberNo);
//        selectCriteriaPlusAlpha.setMessageboxNo(messageboxNo);


        /* selectCriteriaPlusAlpha 조건으로 조회 후 처리하기 위한 코드 */

        // 1. 보여줄 모든 메시지를 담기 : viewAllMessageList를 메소드를 selectCriteria 조건으로 호출해, messageList객체에 담음
        List<MessageDTO> messageList = messageService.viewAllMessageList(messageSelectCriteria);
        log.info("[MessageController] 총 메시지 리스트 : " + messageList);

        // 2. 메시지 리스트와 view 페이지에서 보여주기 위한 정보를 ModelAndView 객체에 담음
        mv.addObject("messageList", messageList);
        mv.addObject("selectCriteria", messageSelectCriteria);
        log.info("[MessageController] 지정된 검색조건 : " + messageSelectCriteria);
        mv.setViewName("content/pm/checkMessage");

        // 3. ModelAndView 객체를 리턴해 목록을 사용자에게 보여줌
        return mv;
    }

    /* 메시지를 삭제하는 메소드 = DB의 'IS_DELETED' 컬럼을 N->Y로 바꿈으로써, 사용자에게만 노출되지 않게 하는 논리적 삭제 실행 */
    @RequestMapping(value = "/delete.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String processDeleteMessageList(HttpServletRequest request, ModelAndView mv, RedirectAttributes rttr, String[] delMess) {

        // 1. 삭제할 메시지들의 메시지번호가 담긴 배열을, 반복문을 돌며 하나씩 삭제
        for (int i = 0; i < delMess.length; i++) {
            log.info("[MessageController] 메시지 번호 " + delMess[i] + "의 삭제 대기 중..");
            messageService.deleteMessage(Integer.valueOf(delMess[i]));
            log.info("[MessageController] " + delMess[i] + "번 메시지 삭제 성공!");
        }

        // 2. 삭제 완료 메시지 출력 후 메시지함으로 돌아감
        log.info("[MessageController] 모든 메시지 삭제 완료 : 페이지를 새로고침 합니다!");
        return "redirect:/pm/checkMessage";
    }

    /* 개별 메시지를 확인하는 메소드 */
    @GetMapping("/readMessage")
    public String readIndividualMessage(HttpServletRequest request, Model model, @RequestParam("nickname") String nickname, @RequestParam("box_type") int box_type) {

        // 1. 어떤 메시지를 보여줄지 확인
        int messageNo = Integer.valueOf(request.getParameter("currentMessage"));

        log.info("[MessageController] 읽기 요청된 메시지 번호 : " + messageNo);

        // 2. 해당 메시지를 보여줄 메소드(viewDetailOfSelectedMessage)를 호출해, 결과를 Model 객체에 담고
        MessageDTO messageDetail = messageService.viewDetailOfSelectedMessage(messageNo);
        log.info("[MessageController] 읽기 요청된 메시지 정보 : " + messageDetail);
        model.addAttribute("message", messageDetail);
        model.addAttribute("nickname", nickname);
        model.addAttribute("box_type", box_type);

        // 3. html 페이지를 호출해 스크립트로 내용을 보여줌
        return "content/pm/readMessage";

    }

    /* 메시지 보내기 Get 메소드 */
    @GetMapping("/sendMessage")
    public String goSendMessage(Model model, @RequestParam("nickname") String nickname, @RequestParam("box_type") int box_type){

        log.info("[MessageController] Sender 는 : '"+nickname+"' (=현재 메시지함 이용자 닉네임)");
        log.info("[MessageController] box_type 은 : '"+box_type+"' (=현재 메시지함 이용자 닉네임의 메시지함)");

        model.addAttribute("nickname", nickname);
        model.addAttribute("box_type", box_type);

        return "content/pm/sendMessage";
    }

    /* 메시지 답장하기 Get 메소드 */
    @GetMapping("/replyMessage")
    public String goReplyMessage(Model model, @RequestParam("replyFor") String messageTitle, @RequestParam("replyTo") String messageSender,
                                 @RequestParam("replyFrom") String nickname, @RequestParam("box_type") int box_type) {

        log.info("[MessageController] '"+messageSender+"'가 보낸 '"+messageTitle+"' 메시지에 대한 답장 요청 확인.");
        log.info("[MessageController] Sender 는 : '"+nickname+"' (=현재 메시지함 이용자 닉네임)");


        // 파라미터에서 값 2개를 취해서 가지고 html로 감
            model.addAttribute("messageTitle", messageTitle);
            model.addAttribute("messageSender", messageSender);
            model.addAttribute("nickname", nickname);
            model.addAttribute("box_type", box_type);
            model.addAttribute("content/pm/replyMessage");

        return "content/pm/replyMessage";
    }

    /* 메시지 보내기 Post 메소드 */
    @PostMapping("/sendMessage")
    public String sendMessage(HttpServletRequest request, @ModelAttribute MessageDTO message,
                              Model model) throws MessageSendException {

        /* 사용자가 메시지를 전송하면, 해당 내용을 DB에 담기 위해 저장될 조건들을 특정 */

        // 1. 조건이 담길 searchMap 객체 생성
        Map<String, Object> searchMap = new HashMap<>();

        // 2. 발신자 닉네임은 해당 메시지박스를 연 유저 닉네임으로 넣음
        objectNickname = request.getParameter("nickname");
        messageboxNo = Integer.valueOf(request.getParameter("nicknamesBoxType"));
        log.info("[MessageController] 발신자 닉네임 : " + objectNickname);
        message.setSenderNickname(objectNickname);

        // 3-1. 먼저 수신자 닉네임을 MessageDTO에서 가져오고, 해당 닉네임으로 수신자의 부모 속성인 회원번호를 검색하고, 검색된 수신자 회원번호를 MessageDTO 객체의 '받는 회원'에 set
        log.info("[MessageController] 수신자 닉네임 : " + message.getReceiverNickname());
        message.setReceiverMemberNo(messageService.getMemberNoByNickname(message.getSenderNickname()));
        log.info("[MessageController] 수신자 유저번호 : " + message.getReceiverMemberNo());
        // 3-1-1. 수신자 닉네임에 연결된 메시지박스 번호를 구해서, 저장될 메시지함 번호로 지정함 -> 그러기 위해 우선 메시지박스를 담을 변수를 선언
        int receiverMessageboxNo = 0;
        // 3-1-1-1. 'Member' 테이블에서 수신자 닉네임이 있는지 확인, 'Member'테이블의 서포터 닉네임이 디폴트 닉네임이기 때문에, 이때 메시지함이 메시지함 번호는 1(서포터 메시지함)
        if(messageService.getMessageboxNoByNicknameFromMember(message.getReceiverNickname())==1){receiverMessageboxNo=1;}
        // 3-1-1-2. 'Member' 테이블에서 수신자 닉네임이 없다면, 'Fundraiser' 테이블에서 닉네임을 찾는다. 이때 Fundraiser는 프로젝트 매니저 역할의 닉네임이므로 메시지함 번호는 2(프로젝트 매니저 메시지함)
        if(messageService.getMessageboxNoByNicknameFromFundrasier(message.getReceiverNickname())==1){receiverMessageboxNo=2;}
        // 3-1-2. 위에서 구한 receiverMessageboxNo를 MessageDTO객체 message에 담음
        message.setBoxType(receiverMessageboxNo);

        // 3-2. 수신자 정보가 set된 MessageDTO객체 message를, DB INSERT할 조건이 담길 searchMap객체에 저장
        searchMap.put("message", message);
        log.info("[MessageController] 다음 메시지에 대한 발신 요청 확인 : " + message);

        // 4. DB에 INSERT할 조건이 담긴 searchMap을 조건으로 해서 서비스의 sendMessage메소드 (DB INSERT구문 호출) 호출
        messageService.sendMessage(searchMap);

        // 5. 페이지 새로고침을 위해, 되돌아갈 변수들을 model에 담고, 새로고침 페이지 (/reloadMessageList) 호출
        model.addAttribute("box_Type", messageboxNo);
        model.addAttribute("success","success");

        return "content/pm/reloadMessageList";
    }

    /* 메시지 답장하기 Post 메소드 */
    @PostMapping("/replyMessage")
    public String replyMessage(HttpServletRequest request, @ModelAttribute MessageDTO message,
                               Model model) throws MessageSendException {

        /* 사용자가 메시지를 전송하면, 해당 내용을 DB에 담기 위해 저장될 조건들을 특정 */

        // 1. 조건이 담길 searchMap 객체 생성
        Map<String, Object> searchMap = new HashMap<>();

        // 2. 발신자 닉네임은 해당 메시지박스를 연 유저 닉네임으로 넣음
        objectNickname = request.getParameter("nickname");
        messageboxNo = Integer.valueOf(request.getParameter("nicknamesBoxType"));
        message.setSenderNickname(objectNickname);

        // 3-1. 먼저 수신자 닉네임을 MessageDTO에서 가져오고, 해당 닉네임으로 수신자의 부모 속성인 회원번호를 검색하고, 검색된 수신자 회원번호를 MessageDTO 객체의 '받는 회원'에 set
        log.info("[MessageController] 수신자 닉네임 : " + message.getReceiverNickname());
        message.setReceiverMemberNo(messageService.getMemberNoByNickname(message.getSenderNickname()));
        log.info("[MessageController] 수신자 유저번호 : " + message.getReceiverMemberNo());
        // 3-1-1. 수신자 닉네임에 연결된 메시지박스 번호를 구해서, 저장될 메시지함 번호로 지정함 -> 그러기 위해 우선 메시지박스를 담을 변수를 선언
        int receiverMessageboxNo = 0;
        // 3-1-1-1. 'Member' 테이블에서 수신자 닉네임이 있는지 확인, 'Member'테이블의 서포터 닉네임이 디폴트 닉네임이기 때문에, 이때 메시지함이 메시지함 번호는 1(서포터 메시지함)
        if(messageService.getMessageboxNoByNicknameFromMember(message.getReceiverNickname())==1){receiverMessageboxNo=1;}
        // 3-1-1-2. 'Member' 테이블에서 수신자 닉네임이 없다면, 'Fundraiser' 테이블에서 닉네임을 찾는다. 이때 Fundraiser는 프로젝트 매니저 역할의 닉네임이므로 메시지함 번호는 2(프로젝트 매니저 메시지함)
        if(messageService.getMessageboxNoByNicknameFromFundrasier(message.getReceiverNickname())==1){receiverMessageboxNo=2;}
        // 3-1-2. 위에서 구한 receiverMessageboxNo를 MessageDTO객체 message에 담음
        message.setBoxType(receiverMessageboxNo);

        // 3-2. 수신자 정보가 set된 MessageDTO객체 message를, DB INSERT할 조건이 담길 searchMap객체에 저장
        searchMap.put("message", message);
        log.info("[MessageController] 다음 메시지에 대한 발신 요청 확인 : " + message);

        // 4. DB에 INSERT할 조건이 담긴 searchMap을 조건으로 해서 서비스의 sendMessage메소드 (DB INSERT구문 호출) 호출
        messageService.sendMessage(searchMap);

        // 5. 페이지 새로고침을 위해, 되돌아갈 변수들을 model에 담고, 새로고침 페이지 (/reloadMessageList) 호출
        model.addAttribute("box_Type", messageboxNo);
        model.addAttribute("success","success");

        return "content/pm/reloadMessageList";

    }


    /* 닉네임 검색 = 닉네임 값을 검색해 해당하는 닉네임값을 넘겨줌 */
    @RequestMapping(value = "/searchNickname", method = {RequestMethod.POST}, produces = "application/json; charset=utf-8")
    public @ResponseBody String[] searchNickname(@RequestParam("nickname") String nickname) throws ParseException {

        String[] searchedNickname = new String[3];
        searchedNickname[0] = null;
        searchedNickname[1] = null;
        searchedNickname[2] = null;

        log.info("[MessageController] 다음 닉네임에 대한 검색 요청 확인 : " + nickname);
        if(nickname!=null){
            searchedNickname = messageService.searchNicknameAndMessageboxNo(nickname);
            System.out.println("[MessageController] searchedNickname[0] = " + searchedNickname[0]);
            System.out.println("[MessageController] searchedNickname[1] = " + searchedNickname[1]);
            System.out.println("[MessageController] searchedNickname[2] = " + searchedNickname[2]);
        }

        return searchedNickname;
    }

    /* 메시지 보낸 후 페이지 리로딩 */
    @GetMapping("/reloadMessageList")
    public Model reloadMessageList(@RequestParam int box_type, @RequestParam(value = "currentPage", defaultValue = "1") int pageNo, Model model) {

        model.addAttribute("box_type", box_type);
        model.addAttribute("pageNo", pageNo);

        return model;
    }
}
