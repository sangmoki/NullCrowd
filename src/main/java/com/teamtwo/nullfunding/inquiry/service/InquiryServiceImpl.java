package com.teamtwo.nullfunding.inquiry.service;

import com.teamtwo.nullfunding.inquiry.model.dao.InquiryMapper;
import com.teamtwo.nullfunding.inquiry.model.dto.InquiryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {

    private InquiryMapper inquiryMapper;

    @Autowired
    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }


    /* 문의사항 추가 */
    @Override
    public int insertInquiry(InquiryDTO inquiry) {

        int result = 0;

        if(inquiry != null) {

            result = inquiryMapper.insertInquiry(inquiry);
        }

        return result;
    }
}
