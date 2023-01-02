package com.teamtwo.nullfunding.notice.controller;

import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public ModelAndView noticeList(ModelAndView mv) {

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();

        mv.addObject("noticeList", noticeList);

        mv.setViewName("content/notice/noticeList");

        return mv;
    }

    // 공지사항 추가하는 용도의 메서드
    @GetMapping("/insert")
    public String goInsert() {
        return "content/notice/noticeInsert";
    }

    @PostMapping("/insertNotice")
    public String insertNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr){

        log.info("");
        log.info("");
        log.info("[NoticeController] registBoard =========================================================");
        log.info("[NoticeController] registBoard Request : " + notice);

        noticeService.insertNotice(notice);

        rttr.addFlashAttribute("message", "공지사항 등록에 성공하셨습니다!");

        log.info("[NoticeController] registBoard =========================================================");

        return "redirect:/notice/list";
    }

    @GetMapping("/detail")
    public String selectNoticeDetail(HttpServletRequest request, Model model) {


        int no = Integer.valueOf(request.getParameter("no"));

        NoticeDTO noticeDetail = noticeService.selectChoiceNotice(no);
        model.addAttribute("notice", noticeDetail);

        return "content/notice/selectChoiceNotice";
    }

    @GetMapping("/update")
    public String updateNotice(HttpServletRequest request, Model model) {

        int no = Integer.valueOf(request.getParameter("no"));

        NoticeDTO notice = noticeService.selectChoiceNotice(no);

        model.addAttribute("notice", notice);

        return "content/notice/noticeUpdate";
    }
}