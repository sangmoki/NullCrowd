package com.teamtwo.nullfunding.notice.controller;

import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    // 공지사항 리스트 조회하는 용도의 메서드
    @GetMapping("/list")
    public ModelAndView noticeList(ModelAndView mv) {

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();

        mv.addObject("noticeList", noticeList);

        mv.setViewName("notice/list");

        return mv;
    }

    // 공지사항 추가하는 용도의 메서드
    @GetMapping("/regist")
    public String goRegister() {
        return "content/notice/noticeInsert";
    }

}