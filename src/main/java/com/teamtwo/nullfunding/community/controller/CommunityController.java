package com.teamtwo.nullfunding.community.controller;

import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    // 모든 게시판 조회
    @GetMapping("/list")
    public ModelAndView communityList(HttpServletRequest request, ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[CommunityController] =========================================================");
        /*
         * 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */
        String currentPage = request.getParameter("currentPage");
        int pageNo = 1;

        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        String searchCondition = request.getParameter("searchCondition");
        String searchValue = request.getParameter("searchValue");

        List<CommunityDTO> communityList = communityService.selectAllCommunityList();
        ModelAndView communityList1 = mv.addObject("communityList", communityList);

        mv.setViewName("content/community/communityList");

        log.info("[CommunityController] =========================================================");
        return mv;
    }
}