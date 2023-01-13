package com.teamtwo.nullfunding.payment.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RefundDTO {

    private String imp_uid;             // 주문번호       " : '<%=email%>' // 주문번호
    private String cancel_amount;       // 환불수량,금액  : 2000,
    private String cancel_reason;       // 환불사유

}
