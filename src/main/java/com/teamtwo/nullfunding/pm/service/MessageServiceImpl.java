package com.teamtwo.nullfunding.pm.service;

import com.teamtwo.nullfunding.common.paging.SelectCriteria;
import com.teamtwo.nullfunding.pm.dao.MessageMapper;
import com.teamtwo.nullfunding.pm.dto.MessageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper mapper;

    public MessageServiceImpl(MessageMapper mapper) {
        this.mapper = mapper;
    }


    // 현재 확인된 닉네임의 모든 메시지 수를 확인하는 메소드
    @Override
    public int checkTotalMessages(Map<String, Integer> specificNickname) {

        int result = mapper.checkTotalMessages(specificNickname);

        return result;
    }

    // 현재 확인된 닉네임의 모든 메시지를 불러하는 메소드
    @Override
    public List<MessageDTO> viewAllMessageList(SelectCriteria selectCriteria) {

        List<MessageDTO> messageList = mapper.viewAllMessageList(selectCriteria);

        return messageList;
    }


    // 현재 로그인된 세션에서, 조건에 맞는 메시지를 불러오는 메소드
    @Override
    public List<MessageDTO> selectMessageByCriteriaList(SelectCriteria selectCriteria) {

        List<MessageDTO> checkMessageList = mapper.selectMessageByCriteriaList(selectCriteria);

        return checkMessageList;
    }

    // 메시지 상세 조회용 메소드
    @Transactional
    @Override
    public MessageDTO viewDetailOfSelectedMessage(int no){
        MessageDTO messageDetail = null;

        boolean isRead = mapper.setRead(no);

        if(isRead){
            messageDetail = mapper.viewDetailOfSelectedMessage(no);
        }

        return messageDetail;
    }

    // 메시지 '읽었음' 표시용 메소드
    @Override
    public boolean setRead(int no) {
        return false;
    }

    // 메시지 발신용 메소드
    @Override
    public String sendMessage(MessageDTO message) {
        return null;
    }

}


