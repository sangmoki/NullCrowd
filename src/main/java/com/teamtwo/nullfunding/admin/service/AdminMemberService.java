package com.teamtwo.nullfunding.admin.service;

import com.teamtwo.nullfunding.admin.model.dto.AdminMemberDTO;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;

import java.util.List;
import java.util.Map;

public interface AdminMemberService {

    // 페이징 처리를 위한 전체 개수 조회
    int selectTotalCount(Map<String, String> searchMap);

    // 모든 회원 정보 조회
    List<AdminMemberDTO> selectAllMemberList(SelectCriteria selectCriteria);

}
