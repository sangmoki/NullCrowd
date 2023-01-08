package com.teamtwo.nullfunding.pm.service;

import com.teamtwo.nullfunding.pm.dto.MessageDTO;

import java.util.List;
import java.util.Map;

public interface MessageService {

    // 닉네임을 확인하는 메소드
    String checkObjectNickname(Map<String, Object> searchMap);

    // 현재 확인된 닉네임의 모든 메시지 수를 확인하는 메소드
    int checkTotalMessages(Map<String, Object> searchMap);

    // 현재 로그인된 세션의 모든 메시지를 불러오는 메소드
    List<MessageDTO> viewAllMessageList(Map<String, Object> selectCriteriaPlusAlpha);

    // 현재 로그인된 세션에서, 조건에 맞는 메시지를 불러오는 메소드
    List<MessageDTO> selectMessageByCheckedList(MessageDTO messageList);

    // 선택한 개별 메시지의 세부내용을 불러오는 메소드
    MessageDTO viewDetailOfSelectedMessage(int no);

    // 메시지 '읽었음' 표시용 메소드
    boolean setRead(int no);

    // 메시지 발신용 메소드
    String sendMessage(MessageDTO message);

    //메시지 삭제용 메소드
    int deleteMessage(Integer messageNo);
}
