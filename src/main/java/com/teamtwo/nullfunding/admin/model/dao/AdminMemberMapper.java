package com.teamtwo.nullfunding.admin.model.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMemberMapper {

    // 페이징 처리를 위한 전체 개수 조회
    int selectTotalCount(Map<String, String> searchMap);

    // 모든 회원 정보 조회
    List<MemberDTO> selectAllMemberList(SelectCriteria selectCriteria);

    List<MemberDTO> selectAllPersonal();
}
