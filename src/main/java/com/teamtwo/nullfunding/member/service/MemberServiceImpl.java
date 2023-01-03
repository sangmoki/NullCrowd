package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.member.dao.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl{//implements MemberService {

//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MemberMapper mapper;

    @Autowired
    public MemberServiceImpl(MemberMapper mapper) {

        this.mapper = mapper;
    }
}

//    public boolean selectMemberById(String memEmail) {
//
//        String result = mapper.selectMemberById(memEmail);
//
//        return result != null? true : false;
//    }
//
//    public boolean insertMember(MemberDTO dto) {
//        int n = mapper.insertMember(dto);
//
//        return n > 0 ? true : false;
//    }

//    @Override
//    public UserDetails loadUserByUsername(String memEmail) throws UsernameNotFoundException {
//
//        /* 로그인 시 사용한 아이디를 가지고 데이터베이스에가서 사용자 정보를 조회 */
//        MemberDTO member = mapper.findMemberById(memEmail);
//
//        if (member == null) {
//            member = new MemberDTO();
//        }
//
////        UserImpl user = new UserImpl(member.getMemEmail(), member.getMemPwd(), authorities);
////        user.setDetails(member);
////        return user;
//    }