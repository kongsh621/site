package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardVO;
import com.example.project01.domain.JjimVO;
import com.example.project01.domain.RatingsVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InfoBoardService {
    List<InfoBoardVO> getList(Criteria criteria);

    List<InfoBoardVO> getRecommendList();
    List<InfoBoardVO> getListByTitle(String title);

    List<InfoBoardVO> getListWithPaging(Criteria criteria1);

    InfoBoardVO readById(Long id);

    boolean write(InfoBoardVO infoBoardVO);

    int getTotal(String type);

    int getSearchTotal(String search);

    List<InfoBoardVO> searchByTitle(Map<String, Object> map);

    List<InfoBoardVO> sortByType(Map<String, Object> map);

    boolean update(InfoBoardVO infoBoardVO);

//    boolean updateTotalRate(InfoBoardVO infoBoardVO);

    boolean delete(Long id);

    // 찜 테이블
    boolean isExistJjim(JjimVO jjimVO);

    // 찜 기능
    boolean changeIsLike(JjimVO jjimVO);

    JjimVO insertJjim(JjimVO jjimVO);


    Set<JjimVO> getJjimList();

    Set<InfoBoardVO> getJjimListWithPaging(HashMap<String, Object>map);

    int getJjimTotal();

    Set<RatingsVO> getRatingsList();


    // ratings 테이블
    boolean insertRating(RatingsVO ratingsVO);

    RatingsVO getAvgByBoard(Long board_id);

    boolean updateTotalRate(InfoBoardVO infoBoardVO);

    boolean isExistRating(RatingsVO ratingsVO);
}
