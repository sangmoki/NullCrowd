package com.teamtwo.nullfunding.pm.dto;

import com.teamtwo.nullfunding.member.dto.FundRaiserDTO;
import com.teamtwo.nullfunding.member.dto.UserImpl;

import java.util.Date;

public class MessageDTO {

    private int messageNo;                          // 메시지 번호(시퀀스)
    private int boxType;                            // 메시지박스 번호(1:member, 2:fundraiser, 3:admin)
    private int receiverMemberNo;                   // 받는사람 회원번호(시퀀스 참조)
    private UserImpl receiverSupporterNickname;     // 받는사람 서포터 닉네임
    private FundRaiserDTO receiverRaiserNickname;   // 받는사람 프로젝터 닉네임
    private String messageTitle;                    // 메시지 제목
    private Date sendDate;                          // 메시지 작성일
    private String senderNickname;                  // 보낸사람 닉네임
    private String messageContent;                  // 메시지 내용
    private boolean isRead;                         // 수신확인
    private boolean isDeleted;                      // 삭제여부

    public MessageDTO() { }

    public MessageDTO(int messageNo, int boxType, int receiverMemberNo, UserImpl receiverSupporterNickname, FundRaiserDTO receiverRaiserNickname, String messageTitle, Date sendDate, String senderNickname, String messageContent, boolean isRead, boolean isDeleted) {
        this.messageNo = messageNo;
        this.boxType = boxType;
        this.receiverMemberNo = receiverMemberNo;
        this.receiverSupporterNickname = receiverSupporterNickname;
        this.receiverRaiserNickname = receiverRaiserNickname;
        this.messageTitle = messageTitle;
        this.sendDate = sendDate;
        this.senderNickname = senderNickname;
        this.messageContent = messageContent;
        this.isRead = isRead;
        this.isDeleted = isDeleted;
    }

    public int getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(int messageNo) {
        this.messageNo = messageNo;
    }

    public int getBoxType() {
        return boxType;
    }

    public void setBoxType(int boxType) {
        this.boxType = boxType;
    }

    public int getReceiverMemberNo() {
        return receiverMemberNo;
    }

    public void setReceiverMemberNo(int receiverMemberNo) {
        this.receiverMemberNo = receiverMemberNo;
    }

    public UserImpl getReceiverSupporterNickname() {
        return receiverSupporterNickname;
    }

    public void setReceiverSupporterNickname(UserImpl receiverSupporterNickname) {
        this.receiverSupporterNickname = receiverSupporterNickname;
    }

    public FundRaiserDTO getReceiverRaiserNickname() {
        return receiverRaiserNickname;
    }

    public void setReceiverRaiserNickname(FundRaiserDTO receiverRaiserNickname) {
        this.receiverRaiserNickname = receiverRaiserNickname;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "messageNo=" + messageNo +
                ", boxType=" + boxType +
                ", receiverMemberNo=" + receiverMemberNo +
                ", receiverSupporterNickname=" + receiverSupporterNickname +
                ", receiverRaiserNickname=" + receiverRaiserNickname +
                ", messageTitle='" + messageTitle + '\'' +
                ", sendDate=" + sendDate +
                ", senderNickname='" + senderNickname + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", isRead=" + isRead +
                ", isDeleted=" + isDeleted +
                '}';
    }
}




