package com.example.project01.mapper;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> getList();

    List<MemberDTO> getListWithPaging(Criteria criteria);

    int save(MemberDTO member);

//    void insertSelectKey(MemberVO member);

    MemberDTO read(Long id);

    int update(MemberDTO memberDTO);

    int updatePass(MemberDTO memberDTO);

    int delete(Long id);

    // 이메일로 조회
    MemberDTO selectMember(MemberDTO memberDTO);
    // 이메일인데 String
    MemberDTO findByEmail(String email);

    int emailCheck(String email);

    long getTotal(Criteria criteria);

    String findEmail(String name);

    int updateKakao(MemberDTO member);
}
