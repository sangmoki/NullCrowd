package com.teamtwo.nullfunding.pm.controller;

import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pm")
public class MessageController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping ("/checkMessage")
    public ModelAndView messageList(ModelAndView mv){

        List<MessageDTO> messageList = messageService.selectAllMessageList();
        log.info("[MessageController] messageList : " + messageList);

        mv.addObject("messageList", messageList);
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
