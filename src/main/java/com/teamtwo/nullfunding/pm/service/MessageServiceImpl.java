package com.teamtwo.nullfunding.pm.service;

import com.teamtwo.nullfunding.common.Exception.message.MessageSendException;
import com.teamtwo.nullfunding.pm.dao.MessageMapper;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import com.teamtwo.nullfunding.pm.dto.MessageSelectCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper mapper;

    public MessageServiceImpl(MessageMapper mapper) {
        this.mapper = mapper;
    }

    // 닉네임을 확인하는 메소드
    @Override
    public String checkObjectNickname(Map<String, Object> searchMap){

        String result = mapper.checkObjectNickname(searchMap);

        return result;

    }


    // 현재 확인된 닉네임의 모든 메시지 수를 확인하는 메소드
    @Override
    public int checkTotalMessages(Map<String, Object> searchMap) {

        int result = mapper.checkTotalMessages(searchMap);

        return result;
    }

    // 현재 확인된 닉네임의 모든 메시지를 불러오는 메소드
    @Override
    public List<MessageDTO> viewAllMessageList(MessageSelectCriteria messageSelectCriteria) {

        List<MessageDTO> messageList = mapper.viewAllMessageList(messageSelectCriteria);

        return messageList;
    }


    // 현재 로그인된 세션에서, 조건에 맞는 메시지를 불러오는 메소드
    @Override
    public List<MessageDTO> selectMessageByCheckedList(MessageDTO messageList) {

        List<MessageDTO> checkMessageList = mapper.selectMessageByCheckedList(messageList);

        return checkMessageList;
    }

    // 메시지 상세 조회용 메소드
    @Transactional
    @Override
    public MessageDTO viewDetailOfSelectedMessage(int messageNo){
        MessageDTO messageDetail = null;

        boolean isRead = mapper.setRead(messageNo);

        if(isRead){
            messageDetail = mapper.viewDetailOfSelectedMessage(Integer.valueOf(messageNo));
        }

        return messageDetail;
    }

    // 메시지 '읽었음' 표시용 메소드
    @Override
    public boolean setRead(int messageNo) {

        boolean result = mapper.setRead(Integer.valueOf(messageNo));
        return result;
    }

    // 닉네임에 딸린 메시지 박스 찾는 메소드1
    @Override
    public int getMessageboxNoByNicknameFromMember(String nickname){

        int result = mapper.getMessageboxNoByNicknameFromMember(nickname);

        return result;
    };

    @Override
    // 닉네임에 딸린 메시지 박스 찾는 메소드2
    public int getMessageboxNoByNicknameFromFundraiser(String nickname){

        int result = mapper.getMessageboxNoByNicknameFromFundraiser(nickname);

        return result;
    }

    // 닉네임에 딸린 가입일 찾는 메소드
    @Override
    public String getRegiDate(String nickname){

         String result = mapper.getRegiDate(nickname);

         return result;
    }


    // 닉네임 검색 및, 닉네임에 딸린 메시지 박스 가져가는 메소드
    @Override
    public String[] searchNicknameAndMessageboxNo(String nickname){

        String[] result = new String[3];

        if(mapper.getMessageboxNoByNicknameFromMember(nickname)==1) {

            result[0] = nickname;
            result[1] = 1+"";
            result[2] = getRegiDate(nickname)+"";

        } else if(mapper.getMessageboxNoByNicknameFromFundraiser(nickname)==1){

            result[0] = nickname;
            result[1] = 2+"";
            result[2] = getRegiDate(nickname)+"";

        }

        return result;
    }

    // 닉네임에 딸린 멤버코드 찾는 메소드
    @Override
    public int getMemberNoByNickname(String nickname){

        int result = mapper.getMemberNoByNickname(nickname);

        return result;

    }


    // 메시지 발신용 메소드
    @Override
    @Transactional
    public void sendMessage(Map<String, Object> message) throws MessageSendException {

        int result = mapper.sendMessage(message);

        if(!(result>0)){
            throw new MessageSendException("메시지 보내기에 실패하였습니다..");
        }

    }

    // 메시지 답장용 메소드
    @Override
    @Transactional
    public void replyMessage(Map<String, Object> message) throws MessageSendException {

        int result = mapper.sendMessage(message);

        if (!(result > 0)) {
            throw new MessageSendException("메시지 보내기에 실패하였습니다..");

        }
    }


    // 메시지 삭제용 메소드
    @Transactional
    public int deleteMessage(Integer deleteMessage) {

        int result = (int) mapper.deleteMessage(deleteMessage);

        return result;
    }


}


