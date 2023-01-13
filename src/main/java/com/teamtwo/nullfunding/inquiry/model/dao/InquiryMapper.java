package com.teamtwo.nullfunding.inquiry.model.dao;

import com.teamtwo.nullfunding.inquiry.model.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryMapper {

    int insertInquiry(InquiryDTO inquiry);

}

