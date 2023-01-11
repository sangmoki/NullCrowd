package com.teamtwo.nullfunding.admin.service;

import com.teamtwo.nullfunding.admin.model.dao.AdminMemberMapper;
import com.teamtwo.nullfunding.admin.model.dto.AdminMemberDTO;
import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public List<AdminMemberDTO> selectAllMemberList(SelectCriteria selectCriteria) {

        List<AdminMemberDTO> memberList = mapper.selectAllMemberList(selectCriteria);

        return memberList;
    }
}
