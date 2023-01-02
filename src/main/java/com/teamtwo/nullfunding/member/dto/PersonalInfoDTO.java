package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PersonalInfoDTO {

    private int memCode;
    private String name;
    private String gender;
    private String phone;
    private String address;
    private Date birthDate;
}
