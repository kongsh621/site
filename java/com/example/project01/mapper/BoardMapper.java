package com.example.project01.mapper;

import com.example.project01.domain.BoardCommentDTO;
import com.example.project01.domain.BoardDTO;
import com.example.project01.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface BoardMapper {

    // board 테이블의 모든 레코드를 조회
    List<BoardDTO> getList();

    List<BoardDTO> getListWithPaging(Criteria criteria);

    // CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알 필요가 없는 경우)
    void insert(BoardDTO board);

    // CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알아야 하는 경우)
    void insertSelectKey(BoardDTO board);

    // READ: PK인 id 컬럼으로 특정 게시물을 조회
    BoardDTO read(Long id);

    // UPDATE: PK인 id 컬럼으로 특정 게시물을 갱신
    // 갱신한 레코드의 개수 반환
    int update(BoardDTO board);

    // DELETE: PK인 id 컬럼으로 특정 게시물을 삭제
    // 삭제한 레코드의 개수 반환
    int delete(Long id);

    int deleteComment(Long id);

    long getTotal();

    int updateHits(Long id);

    List<BoardDTO> searchPost(HashMap<String, Object> map);

    long getSearchTotal(String search);

    BoardDTO findFiles(Long id);

    List<BoardCommentDTO> getCommentWithPaging(HashMap<String, Object> map);

    boolean writeComment(BoardCommentDTO boardCommentDTO);

    long getCommentTotal(Long postid);
}
