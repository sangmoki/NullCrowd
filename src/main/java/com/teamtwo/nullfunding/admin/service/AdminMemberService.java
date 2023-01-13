package com.teamtwo.nullfunding.admin.service;

import com.teamtwo.nullfunding.admin.model.dto.AdminInquiry;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.stereotype.Service;
import com.teamtwo.nullfunding.project.model.dto.ProjectDTO;

import java.util.List;
import java.util.Map;

@Service
public interface AdminMemberService {

    // 페이징 처리를 위한 전체 개수 조회
    int selectTotalCount(Map<String, String> searchMap);

    // 모든 회원 정보 조회
    List<MemberDTO> selectAllMemberList(SelectCriteria selectCriteria);


    int selectInquiryCount(Map<String, Object> map);

    // 모든 문의사항 조회
    List<AdminInquiry> unreadInquiry(Map<String, Object> map);

    List<ProjectDTO> selectAllProject();

    int confirmProject(int projectNo, int decision);
}
