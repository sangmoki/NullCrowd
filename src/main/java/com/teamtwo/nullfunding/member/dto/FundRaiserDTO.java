package com.teamtwo.nullfunding.member.dto;

import lombok.*;

import java.text.SimpleDateFormat;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FundRaiserDTO {

    private int raiserCode; // = memCode
    private String raiNickname;
    private String raiAddress;
    private String raiEmail;
    private String raiPhone;
}
