package com.teamtwo.nullfunding.pm.dao;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.member.dto.UserImpl;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    // 닉네임을 확인하는 메소드
    String checkObjectNickname(Map<String, Object> searchMap);

    // 현재 확인된 닉네임의 모든 메시지 수를 확인하는 메소드
    int checkTotalMessages(Map<String, Object> searchMap);

    // 현재 로그인된 세션의 모든 메시지를 불러오는 메소드
    List<MessageDTO> viewAllMessageList(Map<String, Object> selectCriteriaPlusAlpha);

    // 현재 로그인된 세션에서, 조건에 맞는 메시지를 불러오는 메소드
    List<MessageDTO> selectMessageByCheckedList(MessageDTO messageList);

    // 선택한 개별 메시지의 세부내용을 불러오는 메소드
    MessageDTO viewDetailOfSelectedMessage(Integer messageNo);

    // 메시지 '읽었음' 표시용 메소드
    boolean setRead(Integer messageNo);

    // 닉네임 검색(확인)용 메소드
    int searchNickname(String nickname);

    // 닉네임에 딸린 메시지 박스 찾는 메소드1
    int getMessageboxNoByNicknameFromMember(String nickname);

    // 닉네임에 딸린 메시지 박스 찾는 메소드2
    int getMessageboxNoByNicknameFromFundrasier(String nickname);

    // 닉네임에 딸린 가입일 찾는 메소드
    Date getRegiDate(String nickname);

    // 닉네임에 딸린 메시지 박스 가져가는 메소드
    String searchMessageboxByNickname(String nickname);

    // 닉네임에 딸린 멤버코드 찾는 메소드
    int getMemberNoByNickname(String nickname);


    // 메시지 발신용 메소드
    int sendMessage(Map<String, Object> message);

    //메시지 삭제용 메소드
    int deleteMessage(Integer messageNo);


}
