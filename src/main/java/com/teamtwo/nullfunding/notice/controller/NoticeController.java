package com.teamtwo.nullfunding.notice.controller;

import com.teamtwo.nullfunding.common.exception.notice.NoticeRegistException;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping("/insertNotice")
    public String insertNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws NoticeRegistException {

        log.info("");
        log.info("");
        log.info("[NoticeController] registBoard =========================================================");
        log.info("[NoticeController] registBoard Request : " + notice);

        noticeService.registNotice(notice);

        rttr.addFlashAttribute("message", "공지사항 등록에 성공하셨습니다!");

        log.info("[NoticeController] registBoard =========================================================");

        return "redirect:/notice/list";
    }

    @GetMapping("/detail")
    public String selectNoticeDetail(HttpServletRequest request, Model model) {

        log.info("");
        log.info("");
        log.info("[NoticeController] selectNoticeDetail =========================================================");

        Long no = Long.valueOf(request.getParameter("no"));
        log.info("[NoticeController] selectNoticeDetail No : " + no);

        NoticeDTO noticeDetail = noticeService.selectChoiceNotice(no);
        log.info("[NoticeController] noticeDetail : " + noticeDetail);
        model.addAttribute("notice", noticeDetail);

        log.info("[NoticeController] selectNoticeDetail =========================================================");

        return "content/notice/selectChoiceNotice";
    }

    @GetMapping("/update")
    public String updateNotice(HttpServletRequest request, Model model) {

        Long no = Long.valueOf(request.getParameter("no"));

        NoticeDTO notice = noticeService.selectChoiceNotice(no);

        model.addAttribute("notice", notice);

        return "content/notice/noticeUpdate";
    }
}