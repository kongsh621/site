package com.example.project01.mapper;

import com.example.project01.domain.BoardCommentVO;
import com.example.project01.domain.BoardVO;
import com.example.project01.domain.Criteria;
import com.example.project01.domain.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    // board 테이블의 모든 레코드를 조회
    List<BoardVO> getList();

    List<BoardVO> getListWithPaging(Criteria criteria);

    // CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알 필요가 없는 경우)
    void insert(BoardVO board);

    // CREATE: 새로운 게시물을 등록(추가한 게시물의 id를 알아야 하는 경우)
    void insertSelectKey(BoardVO board);

    // READ: PK인 id 컬럼으로 특정 게시물을 조회
    BoardVO read(Long id);

    // UPDATE: PK인 id 컬럼으로 특정 게시물을 갱신
    // 갱신한 레코드의 개수 반환
    int update(BoardVO board);

    // DELETE: PK인 id 컬럼으로 특정 게시물을 삭제
    // 삭제한 레코드의 개수 반환
    int delete(Long id);

    int deleteComment(Long id);

    long getTotal();

    int updateHits(BoardVO boardVO);

    List<BoardVO> searchPost(HashMap<String, Object> map);

    long getSearchTotal(String search);

    BoardVO findFiles(Long id);

    List<BoardCommentVO> getCommentWithPaging(HashMap<String, Object> map);

    boolean writeComment(BoardCommentVO boardCommentVO);

    long getCommentTotal(Long postid);
}
