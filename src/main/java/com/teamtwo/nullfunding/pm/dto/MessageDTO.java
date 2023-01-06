package com.teamtwo.nullfunding.pm.dto;

import com.teamtwo.nullfunding.member.dto.FundRaiserDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MessageDTO {

    private int messageNo;                          // 메시지 번호(시퀀스)
    private int boxType;                            // 메시지박스 번호(1:member, 2:fundraiser, 3:admin)
    private int receiverMemberNo;                   // 받는사람 회원번호(시퀀스 참조)
    private String receiverNickname;                // 받는사람 닉네임
    private String messageTitle;                    // 메시지 제목
    private Date sendDate;                          // 메시지 작성일
    private String senderNickname;                  // 보낸사람 닉네임
    private String messageContent;                  // 메시지 내용
    private boolean isRead;                         // 수신확인
    private boolean isDeleted;                      // 삭제여부
}