package com.teamtwo.nullfunding.admin.model.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ApproveProjectDTO {

    private int PROJECT_NO;
    private Date PROCESSED_DATE;
    private String IS_APPROVED;
    private String DENIED_RESAON;
}
