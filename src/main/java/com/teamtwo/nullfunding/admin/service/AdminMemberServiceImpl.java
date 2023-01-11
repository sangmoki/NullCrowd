package com.teamtwo.nullfunding.admin.service;

import com.teamtwo.nullfunding.admin.model.dao.AdminMemberMapper;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

    private AdminMemberMapper mapper;

    @Autowired
    public AdminMemberServiceImpl(AdminMemberMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int selectTotalCount(Map<String, String> searchMap) {

        int result = mapper.selectTotalCount(searchMap);

        return result;
    }

    @Override
    public List<MemberDTO> selectAllMemberList(SelectCriteria selectCriteria) {

        List<MemberDTO> memberList = mapper.selectAllMemberList(selectCriteria);

        return memberList;
    }

    @Override
    public List<MemberDTO> selectAllPersonal() {

        List<MemberDTO> personalList = mapper.selectAllPersonal();

        return personalList;
    }
}
