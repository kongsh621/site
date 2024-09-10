package com.example.project01.service;

import com.example.project01.domain.BoardCommentVO;
import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface BoardService {
    // create
    void register(BoardVO board, MultipartFile file) throws IOException;

    // read
    BoardVO get(Long id);

    // update
    Boolean update(BoardVO board);

    // delete
    Boolean delete(Long id);

    Boolean deleteComment(Long id);

    // 게시물 목록 조회
    List<BoardVO> getList();

    List<BoardVO> getListWithPaging(Criteria criteria);

    long getTotal();

    int updateHits(BoardVO boardVO);

    List<BoardVO> findByText(HashMap<String, Object> map);

    long getSearchTotal(String search);

    long getCommentTotal(long postid);

    BoardVO findFiles(Long id);

    List<BoardCommentVO> getCommentWithPaging(HashMap<String, Object> map);

    boolean writeComment(BoardCommentVO boardCommentVO);
}
