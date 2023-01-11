package com.teamtwo.nullfunding.project.model.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class PJDetail {

    private ProjectDTO projectDTO;
    private String raiNickname;
    private int raisedFund; // 현재모금금액
    private int achievePercent; // 달성률
    private int remainDate; // 남은기간.

}
