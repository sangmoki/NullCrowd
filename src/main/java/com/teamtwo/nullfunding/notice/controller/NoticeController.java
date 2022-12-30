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

    @GetMapping("/list")
    public ModelAndView noticeList(ModelAndView mv) {
        log.info("");
        log.info("");
        log.info("[NoticeController] =========================================================");

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();
                log.info("[NoticeController] noticeList : " + noticeList);

        mv.addObject("noticeList", noticeList);

        mv.setViewName("content/notice/noticeList");

        log.info("[NoticeController] =========================================================");

        return mv;
    }
//
//    @GetMapping("/regist")
//    public String goRegister() {
//        return "content/notice/noticeRegist";
//    }
//
//    @PostMapping("/regist")
//    public String registNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) throws NoticeRegistException {
//
//        log.info("");
//        log.info("");
//        log.info("[NoticeController] registBoard =========================================================");
//        log.info("[NoticeController] registBoard Request : " + notice);
//
//        noticeService.registNotice(notice);
//
//        rttr.addFlashAttribute("message", "공지사항 등록에 성공하셨습니다!");
//
//        log.info("[NoticeController] registBoard =========================================================");
//
//        return "redirect:/notice/list";
//    }
//
//    @GetMapping("/detail")
//    public String selectNoticeDetail(HttpServletRequest request, Model model) {
//
//        log.info("");
//        log.info("");
//        log.info("[NoticeController] selectNoticeDetail =========================================================");
//
//        Long no = Long.valueOf(request.getParameter("no"));
//        log.info("[NoticeController] selectNoticeDetail No : " + no);
//
//        NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
//        log.info("[NoticeController] noticeDetail : " + noticeDetail);
//        model.addAttribute("notice", noticeDetail);
//
//        log.info("[NoticeController] selectNoticeDetail =========================================================");
//
//        return "content/notice/noticeDetail";
//    }
//
//    @GetMapping("/update")
//    public String goModifyNotice(HttpServletRequest request, Model model) {
//
//        log.info("");
//        log.info("");
//        log.info("[NoticeController] modifyNotice =========================================================");
//
//        Long no = Long.valueOf(request.getParameter("no"));
//
//        NoticeDTO notice = noticeService.selectNoticeDetail(no);

}