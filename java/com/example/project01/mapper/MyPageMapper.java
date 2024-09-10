package com.example.project01.mapper;

import com.example.project01.domain.BoardCommentVO;
import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {
    // 내 게시글 불러오기
    List<BoardVO> getMyPost(Criteria criteria);
    // 내 댓글 불러오기
    List<BoardCommentVO> getMyComment(Criteria criteria);
    // 내가 작성한 게시글 수
    long getTotalPost(Criteria criteria);
    // 내가 작성한 댓글 수
    long getTotalComment(Criteria criteria);
}
