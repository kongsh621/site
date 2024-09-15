package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.MemberDTO;
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
    public boolean register(MemberDTO memberDTO) {
        int result = 0;
        result = memberMapper.save(memberDTO);
        return true;
    }

    @Override
    public MemberDTO get(Long id) {
        return memberMapper.read(id);
    }

    @Override
    public boolean update(MemberDTO memberDTO){
        return memberMapper.update(memberDTO) == 1;
    }

    @Override
    public boolean updatePass(MemberDTO member) {
        return memberMapper.updatePass(member) == 1;
    }

    @Override
    public boolean emailCheck(String email){ return memberMapper.emailCheck(email) >= 1; }

    @Override
    public boolean delete(Long id){
        return memberMapper.delete(id) == 1;
    }

    @Override
    public List<MemberDTO> getList(){
        return memberMapper.getList();
    }

    @Override
    public MemberDTO selectMember(MemberDTO memberDTO) {
        return memberMapper.selectMember(memberDTO);
    }
    @Override
    public boolean loginMember(MemberDTO memberDTO){
        MemberDTO result = memberMapper.selectMember(memberDTO);
        return result == null ? false : true; // 존재하는 계정인지 확인하려고
    }

    @Override
    public String findByName(String name) { return memberMapper.findEmail(name);}

    @Override
    public List<MemberDTO> getListWithPaging(Criteria criteria){
        return memberMapper.getListWithPaging(criteria);
    }

    @Override
    public long getTotal(Criteria criteria){
        return memberMapper.getTotal(criteria);
    }

    @Override
    public boolean updateKakao(MemberDTO member) {
        return memberMapper.updateKakao(member) == 1;
    }
}
