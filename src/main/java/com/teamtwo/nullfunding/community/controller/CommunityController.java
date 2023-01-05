package com.teamtwo.nullfunding.community.controller;

import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.CommunityDTO;
import com.teamtwo.nullfunding.community.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CommunityService boardService;

    public CommunityController(CommunityService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/list")
    public ModelAndView boardList(HttpServletRequest request, ModelAndView mv) {

        log.info("");
        log.info("");
        log.info("[BoardController] =========================================================");
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

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        log.info("[CommunityController] 컨트롤러에서 검색조건 확인하기 : " + searchMap);

        /*
         * 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */
        int totalCount = boardService.selectTotalCount(searchMap);
        log.info("[CommunityController] totalCommunityCount : " + totalCount);

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;        //얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        log.info("[CommunityController] selectCriteria : " + selectCriteria);

        /* 조회해 온다 */
        List<CommunityDTO> communityList = communityService.selectCommunityList(selectCriteria);

        log.info("[CommunityController] communityList : " + communityList);

        mv.addObject("communityList", communityList);
        mv.addObject("selectCriteria", selectCriteria);
        log.info("[CommunityController] SelectCriteria : " + selectCriteria);
        mv.setViewName("content/community/communityList");

        log.info("[CommunityController] =========================================================");
        return mv;
    }

    @GetMapping("/detail")
    public String selectCommunityDetail(HttpServletRequest request, Model model) {
        log.info("");
        log.info("");
        log.info("[CommunityController] =========================================================");

        Long no = Long.valueOf(request.getParameter("no"));
        CommunityDTO communityDetail = communityService.selectCommunityDetail(no);
        log.info("[CommunityController] communityDetail : " + communityDetail);

        model.addAttribute("community", communityDetail);

        /* 댓글 작성 완료 후 추가할 것 */
        List<ComCommentDTO> comCommentList = communityService.selectAllComCommentList(no);
        model.addAttribute("comCommentList", comCommentList);
        log.info("[CommunityController] comCommentList : " + comCommentList);

        log.info("[CommunityController] =========================================================");
        return "content/community/communityDetail";
    }

    @PostMapping("/registComComment")
    public ResponseEntity<List<ComCommentDTO>> registComComment(@RequestBody ComCommentDTO registComComment) throws ComCommentRegistException {

        log.info("");
        log.info("");
        log.info("[CommunityController] =========================================================");
        log.info("[CommunityController] registComComment Request : " + registComComment);

        List<ComCommentDTO> comCommentList = communityService.registComComment(registComComment);

        log.info("[CommunityController] comCommentList : " + comCommentList);
        log.info("[CommunityController] =========================================================");

        return ResponseEntity.ok(comCommentList);
    }

    @DeleteMapping("/removeComComment")
    public ResponseEntity<List<ComCommentDTO>> removeComComment(@RequestBody ComCommentDTO removeComComment) throws ComCommentRemoveException {

        log.info("");
        log.info("");
        log.info("[CommunityController] =========================================================");
        log.info("[CommunityController] removeComComment Request : " + removeComComment);

        List<ComCommentDTO> comCommentList = communityService.removeComComment(removeComComment);

        log.info("[CommunityController] comCommentList : " + comCommentList);
        log.info("[CommunityController] =========================================================");

        return ResponseEntity.ok(comCommentList);
    }

    @GetMapping("/regist")
    public String goRegister() {
        return "content/community/communityRegist";
    }

    @PostMapping("/regist")
    public String registCommunity(@ModelAttribute CommunityDTO community, RedirectAttributes rttr) throws CommunityRegistException {

        log.info("");
        log.info("");
        log.info("[CommunityController] registCommunity =========================================================");
        log.info("[CommunityController] registCommunity Request : " + community);

        communityService.registCommunity(community);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");

        log.info("[CommunityController] registCommunity =========================================================");

        return "redirect:/community/list";
    }


}