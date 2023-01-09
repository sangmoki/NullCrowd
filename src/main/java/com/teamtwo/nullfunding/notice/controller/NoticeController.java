package com.teamtwo.nullfunding.notice.controller;

import com.teamtwo.nullfunding.common.paging.Pagenation;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.notice.model.dto.NoticeDTO;
import com.teamtwo.nullfunding.notice.service.NoticeService;
import com.teamtwo.nullfunding.notice.service.NoticeServiceImpl;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.lang.model.SourceVersion;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ModelAndView noticeList(@RequestParam(defaultValue = "") String searchCondition,
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
        int totalCount = noticeService.selectTotalCount(searchMap); // Map으로 생성한 searchMap객체를 보내 service에서 담아 갖고 와 totalCount에 담아준다,.

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

        List<NoticeDTO> noticeList = noticeService.selectAllNoticeList(selectCriteria);
        mv.addObject("noticeList", noticeList);
        mv.addObject("selectCriteria", selectCriteria);

        mv.setViewName("content/notice/noticeList");

        return mv;
    }


    // 공지사항 상세보기 페이지

    @GetMapping("/detail")
    public String goNoticeDetail(HttpServletRequest request, Model model) {
        int no = Integer.valueOf(request.getParameter("no"));

        NoticeDTO noticeDetail = noticeService.selectNoticeDetail(no);
        model.addAttribute("detail", noticeDetail);

        return "content/notice/noticeDetail";
    }


//     공지사항 추가하는 용도의 메서드
    @GetMapping("insert")
    public String goInsert( Model model) {

        return "content/notice/noticeInsert";
    }



    // 공지사항 작성하여 전송하기
    @PostMapping("insert")
    public String insertNotice(@ModelAttribute NoticeDTO notice
            , @AuthenticationPrincipal UserDetails userDetails) {

        int memberCode = ((UserImpl)userDetails).getMemCode();

        notice.setMemberCode(memberCode);
        noticeService.insertNotice(notice);

        return "redirect:/notice/list";
    }


    // 공지사항 변경화면
    @GetMapping("/update")
    public String goUpdate(HttpServletRequest request, Model model) {

        int no = Integer.valueOf(request.getParameter("no"));

        NoticeDTO notice = noticeService.selectNoticeDetail(no);

        model.addAttribute("notice", notice);

        return "content/notice/noticeUpdate";
    }

    // 공지사항 작성하여 변경하기
    @PostMapping("/update")
    public String updateNotice(@ModelAttribute NoticeDTO notice){

        noticeService.updateNotice(notice);

    return "redirect:/notice/list";

    }

    // 공지사항 삭제
    @GetMapping("/delete")
    public String deleteNotice(HttpServletRequest request){

        int no = Integer.valueOf(request.getParameter("no"));
        noticeService.deleteNotice(no);

        return "redirect:/notice/list";

    }

//    @RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
//    @ResponseBody
//    public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request )  {
//        JsonObject jsonObject = new JsonObject();
//
//        /*
//         * String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
//         */
//
//        // 내부경로로 저장
//        String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
//        String fileRoot = contextRoot+"resources/fileupload/";
//
//        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
//        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
//        String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
//
//        File targetFile = new File(fileRoot + savedFileName);
//        try {
//            InputStream fileStream = multipartFile.getInputStream();
//            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
//            jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
//            jsonObject.addProperty("responseCode", "success");
//
//        } catch (IOException e) {
//            FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
//            jsonObject.addProperty("responseCode", "error");
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String a = jsonObject.toString();
//        return a;
//    }


}


