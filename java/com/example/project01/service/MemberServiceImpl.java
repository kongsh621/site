package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberVO;
import com.example.project01.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    @Override
    public boolean register(MemberVO memberVO) {
        int result = 0;
        result = memberMapper.save(memberVO);
        return true;
    }

    @Override
    public MemberVO get(Long id) {
        return memberMapper.read(id);
    }

    @Override
    public boolean update(MemberVO memberVO){
        return memberMapper.update(memberVO) == 1;
    }

    @Override
    public boolean updatePass(MemberVO member) {
        return memberMapper.updatePass(member) == 1;
    }

    @Override
    public boolean emailCheck(String email){ return memberMapper.emailCheck(email) >= 1; }

    @Override
    public boolean delete(Long id){
        return memberMapper.delete(id) == 1;
    }

    @Override
    public List<MemberVO> getList(){
        return memberMapper.getList();
    }

    @Override
    public MemberVO selectMember(MemberVO memberVO) {
        return memberMapper.selectMember(memberVO);
    }
    @Override
    public boolean loginMember(MemberVO memberVO){
        MemberVO result = memberMapper.selectMember(memberVO);
        return result == null ? false : true; // 존재하는 계정인지 확인하려고
    }

    @Override
    public String findByName(String name) { return memberMapper.findEmail(name);}

    @Override
    public List<MemberVO> getListWithPaging(Criteria criteria){
        return memberMapper.getListWithPaging(criteria);
    }

    @Override
    public long getTotal(Criteria criteria){
        return memberMapper.getTotal(criteria);
    }
}
