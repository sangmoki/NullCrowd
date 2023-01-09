package com.teamtwo.nullfunding.member.service;

import com.teamtwo.nullfunding.common.Exception.member.MemberInsertException;
import com.teamtwo.nullfunding.member.dao.MemberMapper;
import com.teamtwo.nullfunding.member.dto.MemberDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {


    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MemberMapper mapper;


    @Autowired
    public MemberServiceImpl(MemberMapper mapper) {

        this.mapper = mapper;
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

        return 0;
    }


    @Autowired
    private JavaMailSender mailSender;



    public String getRandomCode(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<7; i++){                           // i에는 랜덤 생성하고 싶은 값의 '자리수+1'을 입력. 7입력 시 6자리 코드.
            if(random.nextBoolean()) {
                sb.append((char) (random.nextInt(26) + 65));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    @Autowired
    public void sendEmail(String toEmail, String subject, String body, String footer) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bs11mailsender@gmail.com");      // 보내는사람
        message.setTo(toEmail);                           // 받는사람
        message.setSubject(subject);                      // 제목
        message.setText("\n"+ body + "\n" + footer);      // body=인증코드, footer=안내멘트

        mailSender.send(message);

        System.out.println("메일이 성공적으로 발송되었습니다!");

    }

}
