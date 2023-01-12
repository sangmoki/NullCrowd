package com.teamtwo.nullfunding.community.controller;

import com.teamtwo.nullfunding.common.Exception.community.*;
import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.community.model.dto.ComCommentDTO;
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
    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    /* 게시글 조회 */
    @GetMapping("/list")
    public ModelAndView communityList(@RequestParam(required = false) String searchCondition,
                                      @RequestParam(required = false) String searchValue,
                                      @RequestParam(value = "currentPage", defaultValue = "1") int pageNo, ModelAndView mv) {
        /* 목록보기를 눌렀을 시 가장 처음에 보여지는 페이지는 1페이지이다.
         * 파라미터로 전달되는 페이지가 있는 경우 currentPage는 파라미터로 전달받은 페이지 수 이다.
         */
        log.info("communityList");

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("searchCondition", searchCondition);
        searchMap.put("searchValue", searchValue);

        /* 전체 게시물 수가 필요하다.
         * 데이터베이스에서 먼저 전체 게시물 수를 조회해올 것이다.
         * 검색조건이 있는 경우 검색 조건에 맞는 전체 게시물 수를 조회한다.
         */
        int totalCount = communityService.selectTotalCount(searchMap);
        // Map으로 생성한 searchMap객체를 보내 service에서 담아 갖고 와 totalCount에 담아준다,.

        /* 한 페이지에 보여 줄 게시물 수 */
        int limit = 10;
        //얘도 파라미터로 전달받아도 된다.

        /* 한 번에 보여질 페이징 버튼의 갯수 */
        int buttonAmount = 5;

        /* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
        SelectCriteria selectCriteria = null;

        if (searchCondition != null && !"".equals(searchCondition)) {
            // 검색조건이 null이 아니고 검색조건과 동일하면 검색조건으로 처리한 select 쿼리문을 들고와 담아준다.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
        } else {  // 만약 둘중 하나라도 아니면 아래 로직을 담는다.
            selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
        }

        List<CommunityDTO> communityList = communityService.selectCommunityList(selectCriteria);

        mv.addObject("communityList", communityList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/community/communityList");

        return mv;
    }

    /* 게시글 상세 페이지 조회 */
    @GetMapping("/detail")
    public String goCommunityDetail(HttpServletRequest request, Model model) {
        log.info("goCommunityDetail");

        int no = Integer.valueOf(request.getParameter("no"));

        CommunityDTO communityDetail = communityService.selectCommunityDetail(no);

        model.addAttribute("detail", communityDetail);

        /* 댓글 작성 완료 후 추가할 것 */
        List<ComCommentDTO> replyList = communityService.selectAllComCommentList(no);
        model.addAttribute("replyList", replyList);

        return "content/community/communityDetail";
    }

    /* 댓글 등록 */
    @PostMapping("/registReply")
    public ResponseEntity<List<ComCommentDTO>> registReply(@RequestBody ComCommentDTO registReply) throws ComCommentRegistException {
        log.info("registReply");

        List<ComCommentDTO> replyList = communityService.registComComment(registReply);

        return ResponseEntity.ok(replyList);
    }

    /* 댓글 삭제 */
    @DeleteMapping("/removeReply")
    public ResponseEntity<List<ComCommentDTO>> removeReply(@RequestBody ComCommentDTO removeReply) throws ComCommentRemoveException {
        log.info("removeReply");

        List<ComCommentDTO> replyList = communityService.removeComComment(removeReply);

        return ResponseEntity.ok(replyList);
    }

    /* 게시글 등록 */
    @GetMapping("/insert")
    public String goRegister() {
        log.info("goRegister");
        return "content/community/communityInsert";
    }

    @PostMapping("/insert")
    public String registCommunity(@ModelAttribute CommunityDTO community, RedirectAttributes rttr) throws CommunityRegistException {
        log.info("registCommunity");

        communityService.registCommunity(community);

        rttr.addFlashAttribute("message", "게시글 등록에 성공하셨습니다!");

        return "redirect:/community/list";
    }

    /* 게시글 수정 */
    @GetMapping("/update")
    public String goModifyCommunity(HttpServletRequest request, Model model) {
        log.info("goModifyCommunity");

        int no = Integer.valueOf(request.getParameter("no"));

        CommunityDTO community = communityService.selectCommunityDetail(no);

        model.addAttribute("community", community);

        return "content/community/communityList";
    }

    @PostMapping("/update")
    public String modifyCommunity(@ModelAttribute CommunityDTO community, RedirectAttributes rttr) throws CommunityModifyException {
        log.info("modifyCommunity");

        communityService.modifyCommunity(community);

        rttr.addFlashAttribute("message", "게시글 수정에 성공하셨습니다!");

        return "redirect:/community/list";
    }

    @GetMapping("/delete")
    public String removeCommunity(HttpServletRequest request, RedirectAttributes rttr) throws CommunityRemoveException {

        int no = Integer.valueOf(request.getParameter("no"));

        communityService.removeCommunity(no);

        rttr.addFlashAttribute("message", "게시글 삭제에 성공하셨습니다!");

        return "redirect:/community/list";
    }
}