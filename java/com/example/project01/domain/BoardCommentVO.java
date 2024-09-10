package com.example.project01.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardCommentVO {
    private Long id;
    private String writer;
    private String content;
    private Date regdate;
    private Long postid; // 게시글별 댓글 출력용
}
