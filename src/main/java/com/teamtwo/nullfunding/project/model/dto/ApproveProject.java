package com.teamtwo.nullfunding.project.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApproveProject {

    private int projectNo;
    private String processedDate; //처리날짜
    private int isApproved; // 1:승인 2:반려 3:처리대기(default)
    private String deniedReason; // 반려사유
}
