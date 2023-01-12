package com.teamtwo.nullfunding.payment.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaymentDTO {

    // 사전 정보
    private int memberNo;                    // 구매자 회원 번호
    private String content;                 // 구매 품목
    private int quantity;                   // 구매 수량

    // 결제 정보
    private String pg;                      // 결제사?
    private String pay_method;              // 결제방식 : "card",
    private String imp_uid;                 // 결제번호 id,
    private String merchant_uid;            // 판매자 id : "ORD20180131-0000011",
    private String name;                    // 대상 물품 : "노르웨이 회전 의자",
    private int amount;                     // 금액 : 64900,
    private String buyer_email;             // 구매자 이메일 : "gildong@gmail.com",
    private String buyer_name;              // 구매자 이름 : "홍길동",
    private String buyer_tel;               // 구매자 전화번호 : "010-4242-4242",
    private String buyer_addr;              // 구매자 주소 : "서울특별시 강남구 신사동",
    private int buyer_postcode;             // 구매자 우편번호 : "01181"

}
