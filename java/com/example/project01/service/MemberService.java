package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberVO;
import com.example.project01.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    boolean register(MemberVO memberVO);

    MemberVO get(Long id);

    MemberVO selectMember(MemberVO memberVO);

    boolean loginMember(MemberVO memberVO);

    boolean emailCheck(String email);

    boolean delete(Long id);

    List<MemberVO> getList();

    String findByName(String name);

    List<MemberVO> getListWithPaging(Criteria criteria);
    boolean update(MemberVO member);

    boolean updatePass(MemberVO member);

    long getTotal(Criteria criteria);

    boolean updateKakao(MemberVO member);
}
