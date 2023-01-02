package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dao.MemberMapper;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    private final MemberMapper mapper;

    public MemberService(MemberMapper mapper) {
        this.mapper = mapper;
    }

    public boolean selectMemberById(String memEmail) {

        String result = mapper.selectMemberById(memEmail);

        return result != null? true : false;
    }

    public boolean insertMember(MemberDTO dto) {
        int n = mapper.insertMember(dto);

        return n > 0 ? true : false;
    }
}

