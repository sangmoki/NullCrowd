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
    private String pg;         // 결제방식 : "kakaopay",
    private String pay_method; // 결제수단 : "kakaopay"
    private String imp_uid;                 // (응답용) 아임포트에서 발급해주는 고유 UID
    private String merchant_uid;            // DB에서 시퀀스 생성해 가져와서 담아야 함
    private String name;                    // 대상 물품 : 필수는 아님.
    private int amount = 10000000;          // 결제금액 : 계산해서 담아야 함
    private String buyer_email;             // 구매자 이메일 : "gildong@gmail.com",
    private String buyer_name;              // 구매자 이름 : "홍길동",
    private String buyer_tel;               // 구매자 전화번호 : "010-4242-4242",
    private String buyer_addr;              // 구매자 주소 : "서울특별시 강남구 신사동",

}
