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

    private int projectNo; // 시퀀스
    private int raiserCode; // userImpl
    private String title; // 프로젝트 이름
    private String description;  //프로젝트 content 부분
    private int fundGoal; // 목표금액
    private Date regiDate; // 등록 날짜, sysdate
    private Date startDate; //시작 날짜
    private Date endDate; //종료 날짜
    private char isFundable; //후원가능여부 (시작날짜, 종료날짜 사이 =Y, 그 외 N)
    private int funderNum; // 참여자수
    private String mainImg; // 메인이미지주소값(대표이미지)

    private List<ProjectRewardDTO> projectRewardDTOList; // 리워드 별 이름,가격,내용
    private List<ProjectMediaDTO> projectMediaList;
}