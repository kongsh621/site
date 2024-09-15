package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberDTO;

import java.util.List;

public interface MemberService {
    boolean register(MemberDTO memberDTO);

    MemberDTO get(Long id);

    MemberDTO selectMember(MemberDTO memberDTO);

    boolean loginMember(MemberDTO memberDTO);

    boolean emailCheck(String email);

    boolean delete(Long id);

    List<MemberDTO> getList();

    String findByName(String name);

    List<MemberDTO> getListWithPaging(Criteria criteria);
    boolean update(MemberDTO member);

    boolean updatePass(MemberDTO member);

    long getTotal(Criteria criteria);

    boolean updateKakao(MemberDTO member);
}
