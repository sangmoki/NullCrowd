package com.teamtwo.nullfunding.notice.controller;

import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    // 모든 공지사항 조회
    @GetMapping("/list")
    public ModelAndView noticeList(ModelAndView mv) {

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList();
        mv.addObject("noticeList", noticeList);

        mv.setViewName("content/notice/noticeList");

        return mv;
    }

    // 공지사항 검색하여 조회
//    @GetMapping("/list")
//    public ModelAndView selectChoiceNotice(ModelAndView mv){
//
//        List<NoticeDTO> noticeList = noticeService.selectChoiceNotice();
//        mv.addObject("noticeList", noticeList);
//
//        mv.setViewName("content/notice/noticeList");
//
//        return mv;
//    }

    // 공지사항 상세보기 페이지
//    @GetMapping("/list")
//    public ModelAndView goChoiceNotice(ModelAndView mv) {
//
//        int no = 0;
//
//        List<NoticeDTO> noticeList = noticeService.selectChoiceNotice(no);
//        mv.addObject("noticeList", noticeList);
//
//        mv.setViewName("content/notice/noticeList");
//
//        return mv;
//    }


//     공지사항 추가하는 용도의 메서드
    @GetMapping("insert")
    public String goInsert() {
                        // @AuthenticationPrincipal UserDetails userDetails, Model model
//        model.addAttribute("memberCode", ((UserImpl)userDetails).getMemCode());

        return "content/notice/noticeInsert";
    }

    @PostMapping("insert")

    public String insertNotice(@ModelAttribute NoticeDTO notice, RedirectAttributes rttr) {


        noticeService.insertNotice(notice);

        rttr.addFlashAttribute("message", "공지사항 등록에 성공하셨습니다!");

        return "redirect:/notice/list";
    }


//    @GetMapping("/update")
//    public String updateNotice(HttpServletRequest request, Model model) {
//
//        int no = Integer.valueOf(request.getParameter("no"));
//
//        NoticeDTO notice = noticeService.updateNotice(no);
//
//        model.addAttribute("notice", notice);
//
//        return "content/notice/noticeUpdate";
//    }
}


