package com.example.project01.domain;

import lombok.Getter;
import lombok.ToString;

// 페이지 화면 처리를 담당하는 클래스

@Getter
@ToString
public class PageMaker {
    // 전체 게시물 개수
    private long total;

    // 페이지에 보여줄 게시물 개수
    // 현재 페이지 번호
    private Criteria criteria;

    private int pagesPerViewport;

    private int lastPage;

    private int startPage;

    private int endPage;

    private boolean prev;

    private boolean next;

    public PageMaker(Criteria criteria, long total, int pagesPerViewport){
        this.criteria = criteria;
        this.total = total;
        this.pagesPerViewport = pagesPerViewport;

        // 마지막 페이지 번호
        lastPage = (int)Math.ceil((double)total / criteria.getRowsPerPage());

        // 화면에 보여줄 페이지의 마지막 번호
        endPage = (int)Math.ceil((double)criteria.getPage()/pagesPerViewport) * pagesPerViewport;

        startPage = (endPage - pagesPerViewport) + 1;

        if (startPage <= 0) startPage = 1;

        if (lastPage < endPage) endPage = lastPage;

        prev = startPage > 1;

        next = endPage < lastPage;
    }

    public PageMaker(Criteria criteria, long total){
        this(criteria, total, 10);
    }


}
