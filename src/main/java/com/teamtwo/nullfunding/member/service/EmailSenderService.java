package com.teamtwo.nullfunding.member.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailSenderService {

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
