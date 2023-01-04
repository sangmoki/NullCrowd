package com.teamtwo.nullfunding.project.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectMediaDTO {

    private int mediaNo;
    private int projectNo; // = from 프로젝트 펀딩 (PK +FK)
    private String fileName;
    private String hashName; // 저장이름
    private String mediaType; // image/video
    private String url; // 저장 url경로
    private char isDeleted; // 삭제여부 ('Y', 'N')

}
