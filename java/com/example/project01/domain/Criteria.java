package com.example.project01.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

// 페이징 처리의 기준이 되는 Criteria 클래스
@Data
@AllArgsConstructor
public class Criteria {
    // 현재 페이지
    private int page;

    // 페이지당 보여줄 게시물 개수
    private int rowsPerPage;

    // 내가 쓴 글 댓글
    private String writer;


//    public Criteria(int page, int rowsPerPage) {this(page, rowsPerPage, null);}

    public Criteria(){
        // 기본 생성자에는 기본 값을 설정
        this(1, 10, null);
    }
}
