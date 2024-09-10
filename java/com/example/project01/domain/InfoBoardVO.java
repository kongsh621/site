package com.example.project01.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class InfoBoardVO {
    private Long id;
    private String title;
    private String actor;
    private String content;
    private String regdate; // 개봉일로 써야지 그래서 Date로 안 썼다
    private int rate; // 로그인 멤버 최근 평점
    private Double total_rate; // 평점 평균 // ratings로 구해서 여기에 저장
    private int total; // 작품당 평가수 // 출력용
    private String type;
}
