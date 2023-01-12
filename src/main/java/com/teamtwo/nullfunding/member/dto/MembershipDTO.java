package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MembershipDTO {

    private int membershipNo;

    private int memberNo;

    private String startDate;

    private String endDate;

    private boolean isValid;

}
