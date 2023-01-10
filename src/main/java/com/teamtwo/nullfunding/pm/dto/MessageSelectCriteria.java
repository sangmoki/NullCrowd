package com.teamtwo.nullfunding.pm.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageSelectCriteria {

    /* pm 추가 SelectCriteria */

    private int memberNo;                   // 회원 번호
    private int messageboxNo;               // 메시지 박스 번호


    /* common SelectCriteria */

    private int pageNo;                     //요청한 페이지 번호
    private int totalCount;                 //전체 메시지 수
    private int limit;                      //한 페이지에 보여줄 메시지 수
    private int buttonAmount;               //한 번에 보여줄 페이징 버튼의 갯수
    private int maxPage;                    //가장 마지막 페이지
    private int startPage;                  //한 번에 보여줄 페이징 버튼의 시작하는 페이지 수
    private int endPage;                    //한 번에 보여줄 페이징 버튼의 마지막 페이지 수
    private int startRow;                   //DB 조회 시 메시지 글 부터 조회해야 하는 행의 시작 수
    private int endRow;                     //DB 조회 시 메시지 조회해야 하는 행의 마지막 수

}