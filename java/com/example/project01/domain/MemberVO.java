package com.example.project01.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private int age;
    private Date regdate;
    private Date updatedate;

    public MemberVO(String email, String password){ this(null, email, password, null, null, 0, null, null);}
}
