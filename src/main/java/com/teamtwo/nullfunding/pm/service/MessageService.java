package com.teamtwo.nullfunding.pm.service;

import com.teamtwo.nullfunding.common.Exception.message.MessageSendException;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.dto.MessageSelectCriteria;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MessageService {

    // 닉네임을 확인하는 메소드
    String checkObjectNickname(Map<String, Object> searchMap);

    // 현재 확인된 닉네임의 모든 메시지 수를 확인하는 메소드
    int checkTotalMessages(Map<String, Object> searchMap);

    // 현재 로그인된 세션의 모든 메시지를 불러오는 메소드
    List<MessageDTO> viewAllMessageList(MessageSelectCriteria messageSelectCriteria);

    // 현재 로그인된 세션에서, 조건에 맞는 메시지를 불러오는 메소드
    List<MessageDTO> selectMessageByCheckedList(MessageDTO messageList);

    // 선택한 개별 메시지의 세부내용을 불러오는 메소드
    MessageDTO viewDetailOfSelectedMessage(int messageNo);

    // 메시지 '읽었음' 표시용 메소드
    boolean setRead(int messageNo);

    // 닉네임에 딸린 메시지 박스 찾는 메소드1
    int getMessageboxNoByNicknameFromMember(String nickname);

    // 닉네임에 딸린 메시지 박스 찾는 메소드2
    int getMessageboxNoByNicknameFromFundrasier(String nickname);

    // 닉네임에 딸린 가입일 찾는 메소드
    Date getRegiDate(String nickname);

    // 닉네임 검색 및, 닉네임에 딸린 메시지 박스 가져가는 메소드
    public String[] searchNicknameAndMessageboxNo(String nickname);

    // 닉네임에 딸린 멤버코드 찾는 메소드
    int getMemberNoByNickname(String nickname);

    // 메시지 발신용 메소드
    void sendMessage(Map<String, Object> searchMap) throws MessageSendException;

    // 메시지 답장용 메소드
    void replyMessage(Map<String, Object> searchMap) throws MessageSendException;


    //메시지 삭제용 메소드
    int deleteMessage(Integer messageNo);
}
