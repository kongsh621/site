package com.example.project01.service;

import com.example.project01.domain.BoardCommentDTO;
import com.example.project01.domain.BoardDTO;
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
    public void register(BoardDTO board, MultipartFile file) throws IOException{
        log.info("register = {}", board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardDTO get(Long id) {
        log.info("id = {}", id);

        return mapper.read(id);
    }

    @Override
    public Boolean update(BoardDTO board) {
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
    public List<BoardDTO> getList() {
        log.info("getList");

        return mapper.getList();

    }

    @Override
    public List<BoardDTO> getListWithPaging(Criteria criteria){
        log.info("getList with criteria = {}", criteria);

        return mapper.getListWithPaging(criteria);
    }

    @Override
    public long getTotal(){

        return mapper.getTotal();
    }

    @Override
    public int updateHits(Long id) {
        return mapper.updateHits(id);
    }

    @Override
    public List<BoardDTO> findByText(HashMap<String, Object> map) {
        return mapper.searchPost(map);
    }

    @Override
    public long getSearchTotal(String search) {
        return mapper.getSearchTotal(search);
    }

    @Override
    public long getCommentTotal(long postid){ return mapper.getCommentTotal(postid);}

    @Override
    public BoardDTO findFiles(Long id) {
        return mapper.findFiles(id);
    }

    @Override
    public List<BoardCommentDTO> getCommentWithPaging(HashMap<String, Object> map) {
        return mapper.getCommentWithPaging(map);
    }

    @Override
    public boolean writeComment(BoardCommentDTO boardCommentDTO) {
        return mapper.writeComment(boardCommentDTO);
    }
}
