package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.common.Exception.member.MemberInsertException;
import com.teamtwo.nullfunding.member.dao.MemberMapper;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MemberMapper mapper;

    private JavaMailSender mailSender;


    @Autowired
    public MemberServiceImpl(MemberMapper mapper, JavaMailSender mailSender) {

        this.mapper = mapper;
        this.mailSender = mailSender;
    }

    @Override
    public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {

        /* 로그인 시 사용한 아이디를 가지고 데이터베이스에가서 사용자 정보를 조회 */
        MemberDTO member = mapper.findMemberById(memEmail);

        if (member == null) {
            member = new MemberDTO();
        }
        String authority = member.getAuthName();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(authority));
        System.out.println("member = " + member);
        UserImpl user = new UserImpl(member.getMemEmail(), member.getMemPwd(), authorities);
        user.setDetails(member);
        return user;

    }


//    public int insertMember(MemberDTO member) {
//        int result = mapper.insertMember(member);
//        return result;
//    }

    @Transactional
    public void insertMember(MemberDTO member) throws MemberInsertException{

        log.info("[MemberService] Insert Member : " + member);
        int result = mapper.insertMember(member);

        log.info("[MemberService] Insert result : " + ((result > 0)? "회원가입 성공" : "회원가입 실패"));

        if(!(result > 0)){
            throw new MemberInsertException("회원 가입에 실패하였습니다.");
        }
    }

    @Override
    public int idDupCheck(String memEmail) {

        int result = mapper.idDupCheck(memEmail);

        return result;
    }

}
