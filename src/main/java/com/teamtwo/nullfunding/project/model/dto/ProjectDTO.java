package com.teamtwo.nullfunding.project.model.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private int projectNo;
    private String description;  //프로젝트 content 부분
    private int fundGoal; // 목표금액
    private Date regiDate; // 등록 날짜
    private Date startDate; //시작 날짜
    private Date endDate; //종료 날짜
    private char isFundable; //후원가능여부

    private List<ProjectMediaDTO> projectMediaList;
}
