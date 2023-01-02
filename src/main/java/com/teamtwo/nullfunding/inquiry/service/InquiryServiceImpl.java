package com.teamtwo.nullfunding.inquiry.service;

import com.teamtwo.nullfunding.inquiry.model.dao.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquiryServiceImpl implements InquiryService {

    private InquiryMapper inquiryMapper;

    @Autowired
    public InquiryServiceImpl(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

}
