package com.teamtwo.nullfunding.admin.controller;

import com.teamtwo.nullfunding.admin.model.dto.AdminInquiry;
import com.teamtwo.nullfunding.admin.service.AdminMemberService;
import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.inquiry.model.dto.InquiryDTO;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminMemberController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private AdminMemberService adminMemberService;

    @Autowired
    public AdminMemberController(AdminMemberService adminMemberService) {
        this.adminMemberService = adminMemberService;
    }

    @GetMapping("/dashBoard")
    public ModelAndView adminDashBoard(ModelAndView mv){

        mv.setViewName("/content/admin/dashBoard");

        return mv;
    }


    /* 회원 관리 부분 */
    @GetMapping("/member")
    public ModelAndView adminMember(@RequestParam(defaultValue = "") String searchCondition,
                                    @RequestParam(defaultValue = "") String searchValue,
                                    @RequestParam(value="currentPage", defaultValue = "1") int pageNo, ModelAndView mv) {


        /* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */
        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        /* 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */
        int totalCount = adminMemberService.selectTotalCount(searchMap); // Map으로 생성한 searchMap객체를 보내 service에서 담아 갖고 와 totalCount에 담아준다,.

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;		//얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if(searchCondition != null && !"".equals(searchCondition)) { // 검색조건이 null이 아니고 검색조건과 동일하면 검색조건으로 처리한 select 쿼리문을 들고와 담아준다.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {  // 만약 둘중 하나라도 아니면 아래 로직을 담는다.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<MemberDTO> memberList = adminMemberService.selectAllMemberList(selectCriteria);
        mv.addObject("memberList", memberList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/admin/member");

        return mv;
    }


    /* 문의 관리 부분 */
    @GetMapping("/inquiry")
    public ModelAndView adminInquiry(@RequestParam String readYn,
                                    @RequestParam(value="currentPage", defaultValue = "1") int pageNo,
                                     ModelAndView mv) {

        System.out.println("readYn = " + readYn);
        Map<String, Object> map = new HashMap<>();
        map.put("readYn", readYn);
        int totalCount = adminMemberService.selectInquiryCount(map); // Map으로 생성한 searchMap객체를 보내 service에서 담아 갖고 와 totalCount에 담아준다,.
        System.out.println("totalCount = " + totalCount);
        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;		//얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;


        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

        map.put("selectCriteria",selectCriteria);
        List<AdminInquiry> unreadInquiryList = adminMemberService.unreadInquiry(map);
        mv.addObject("unreadInquiryList", unreadInquiryList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/admin/inquiry");

        return mv;
    }


    /* 프로젝트 관리 부분 */
    @GetMapping("/project")
    public ModelAndView adminProject(ModelAndView mv){

        mv.setViewName("content/admin/project");

        return mv;
    }

    /* 매출 관리 부분 */
    @GetMapping("/sales")
    public ModelAndView adminSales(ModelAndView mv){

        mv.setViewName("content/admin/sales");

        return mv;
    }

    /* 핫펀딩 부분 */
    @GetMapping("/hot")
    public ModelAndView adminHot(ModelAndView mv){

        mv.setViewName("content/admin/hot");

        return mv;
    }

    /* 알림/메세지 부분 */
    @GetMapping("/message")
    public ModelAndView adminMessage(ModelAndView mv){

        mv.setViewName("content/admin/message");

        return mv;
    }







}
