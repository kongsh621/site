package com.example.project01.domain;

import lombok.Data;

@Data
public class JjimVO {
    private Long member_id; // 외래키
    private Long infoboard_id; // 외래키
    private String  is_like;
}
