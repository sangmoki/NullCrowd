package com.teamtwo.nullfunding.payment.service;

import com.teamtwo.nullfunding.payment.dao.PaymentMapper;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PaymentServiceImpl implements  PaymentService{

    private PaymentMapper mapper;

    public PaymentServiceImpl(PaymentMapper mapper){

        this.mapper = mapper;

    }



}
