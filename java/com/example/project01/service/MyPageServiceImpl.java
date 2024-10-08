package com.example.project01.service;

import com.example.project01.domain.BoardCommentDTO;
import com.example.project01.domain.BoardDTO;
import com.example.project01.domain.Criteria;
import com.example.project01.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPageServiceImpl implements MyPageService {
    @Autowired
    private MyPageMapper mapper;

    @Override
    public List<BoardDTO> getMyPost(Criteria criteria) {
        return mapper.getMyPost(criteria);
    }

    @Override
    public List<BoardCommentDTO> getMyComment(Criteria criteria) {
        return mapper.getMyComment(criteria);
    }

    @Override
    public long getTotalPost(Criteria criteria) {
        return mapper.getTotalPost(criteria);
    }

    @Override
    public long getTotalComment(Criteria criteria) {
        return mapper.getTotalComment(criteria);
    }
}
