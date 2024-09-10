package com.example.project01.service;

import com.example.project01.domain.BoardCommentVO;
import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import com.example.project01.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// 비즈니스 계층을 담당하는 클래스임을 명시
@Service

@AllArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board, MultipartFile file) throws IOException{
        log.info("register = {}", board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVO get(Long id) {
        log.info("id = {}", id);

        return mapper.read(id);
    }

    @Override
    public Boolean update(BoardVO board) {
        log.info("update = {}", board);

        return mapper.update(board) == 1;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("delete = {}", id);

        return mapper.delete(id) == 1;
    }

    @Override
    public Boolean deleteComment(Long id){
        return mapper.deleteComment(id) == 1;
    }

    @Override
    public List<BoardVO> getList() {
        log.info("getList");

        return mapper.getList();

    }

    @Override
    public List<BoardVO> getListWithPaging(Criteria criteria){
        log.info("getList with criteria = {}", criteria);

        return mapper.getListWithPaging(criteria);
    }

    @Override
    public long getTotal(){

        return mapper.getTotal();
    }

    @Override
    public int updateHits(BoardVO boardVO) {
        return mapper.updateHits(boardVO);
    }

    @Override
    public List<BoardVO> findByText(HashMap<String, Object> map) {
        return mapper.searchPost(map);
    }

    @Override
    public long getSearchTotal(String search) {
        return mapper.getSearchTotal(search);
    }

    @Override
    public long getCommentTotal(long postid){ return mapper.getCommentTotal(postid);}

    @Override
    public BoardVO findFiles(Long id) {
        return mapper.findFiles(id);
    }

    @Override
    public List<BoardCommentVO> getCommentWithPaging(HashMap<String, Object> map) {
        return mapper.getCommentWithPaging(map);
    }

    @Override
    public boolean writeComment(BoardCommentVO boardCommentVO) {
        return mapper.writeComment(boardCommentVO);
    }
}
