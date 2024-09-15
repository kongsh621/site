package com.example.project01.domain;

import lombok.Data;

@Data
public class RatingsDTO {
    private Long id;
    private Long member_id;
    private Long board_id;
    private Double rating; // 1인 평점 // rate에서 받아와서 저장 // sum으로 작품별 총 평가 점수 구하기
    private Integer count; // 회원-작품 평가 횟수 (0~1) // sum으로 작품별 평가 횟수 구하기
    private Integer total; // 평점 합계 // 필요 없음
}
