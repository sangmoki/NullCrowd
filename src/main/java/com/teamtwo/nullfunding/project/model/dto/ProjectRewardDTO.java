package com.teamtwo.nullfunding.project.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRewardDTO {

    private int projectNo; // 프로젝트 코드 (PK + FK)
    private String name; // 리워드 명
    private int price; // 리워드 가격
    private String details; // 리워드 내용
}
