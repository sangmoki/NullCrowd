package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonalInfoDTO {

    private int memCode;
    private String phone;
    private Date birthDate;
    private String address;
    private String gender;
    private String name;
}
