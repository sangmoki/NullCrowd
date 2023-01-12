package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembershipDTO {

    private int memberNo;
    private int membershipNo;
    private Date startDate;
    private Date endDate;
    private boolean isValid;

}
