package com.example.project01.service;

import com.example.project01.domain.BoardCommentDTO;
import com.example.project01.domain.BoardDTO;
import com.example.project01.domain.Criteria;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface BoardService {
    // create
    void register(BoardDTO board, MultipartFile file) throws IOException;

    // read
    BoardDTO get(Long id);

    // update
    Boolean update(BoardDTO board);

    // delete
    Boolean delete(Long id);

    Boolean deleteComment(Long id);

    // 게시물 목록 조회
    List<BoardDTO> getList();

    List<BoardDTO> getListWithPaging(Criteria criteria);

    long getTotal();

    int updateHits(Long id);

    List<BoardDTO> findByText(HashMap<String, Object> map);

    long getSearchTotal(String search);

    long getCommentTotal(long postid);

    BoardDTO findFiles(Long id);

    List<BoardCommentDTO> getCommentWithPaging(HashMap<String, Object> map);

    boolean writeComment(BoardCommentDTO boardCommentDTO);
}
