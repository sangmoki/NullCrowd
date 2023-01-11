package com.teamtwo.nullfunding.inquiry.service;


import com.teamtwo.nullfunding.inquiry.model.dao.InquiryMapper;
import com.teamtwo.nullfunding.inquiry.model.dto.InquiryDTO;

public interface InquiryService {

    int insertInquiry(InquiryDTO inquiry);
}
