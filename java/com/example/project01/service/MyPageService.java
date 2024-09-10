package com.example.project01.service;

import com.example.project01.domain.BoardCommentVO;
import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import com.example.project01.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyPageService {

    // 내 게시글 불러오기
    List<BoardVO> getMyPost(Criteria criteria);
    // 내 댓글 불러오기
    List<BoardCommentVO> getMyComment(Criteria criteria);
    // 내가 작성한 게시글 수
    long getTotalPost(Criteria criteria);
    // 내가 작성한 댓글 수
    long getTotalComment(Criteria criteria);
}
