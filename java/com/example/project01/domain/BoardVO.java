package com.example.project01.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardVO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private long hits;
    private Date regdate;
    private Date updatedate;

    private String filename;
    private String filepath;
}
