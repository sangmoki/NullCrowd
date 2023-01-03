package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dao.MemberMapper;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService{

    private MemberMapper mapper;

    @Autowired
    public MemberServiceImpl(MemberMapper mapper) {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

