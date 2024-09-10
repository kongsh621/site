package com.example.project01.mapper;

import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberVO> getList();

    List<MemberVO> getListWithPaging(Criteria criteria);

    int save(MemberVO member);

//    void insertSelectKey(MemberVO member);

    MemberVO read(Long id);

    int update(MemberVO memberVO);

    int updatePass(MemberVO memberVO);

    int delete(Long id);

    // 이메일로 조회
    MemberVO selectMember(MemberVO memberVO);
    // 이메일인데 String
    MemberVO findByEmail(String email);

    int emailCheck(String email);

    long getTotal(Criteria criteria);

    String findEmail(String name);
}
