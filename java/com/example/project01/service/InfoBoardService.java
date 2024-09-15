package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardDTO;
import com.example.project01.domain.JjimDTO;
import com.example.project01.domain.RatingsDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface InfoBoardService {
    List<InfoBoardDTO> getList(Criteria criteria);

    List<InfoBoardDTO> getRecommendList();
    List<InfoBoardDTO> getListByTitle(String title);

    List<InfoBoardDTO> getListWithPaging(Criteria criteria1);

    InfoBoardDTO readById(Long id);

    boolean write(InfoBoardDTO infoBoardDTO);

    int getTotal(String type);

    int getSearchTotal(String search);

    List<InfoBoardDTO> searchByTitle(Map<String, Object> map);

    List<InfoBoardDTO> sortByType(Map<String, Object> map);

    boolean update(InfoBoardDTO infoBoardDTO);

//    boolean updateTotalRate(InfoBoardVO infoBoardVO);

    boolean delete(Long id);

    // 찜 테이블
    boolean isExistJjim(JjimDTO jjimDTO);

    // 찜 기능
    boolean changeIsLike(JjimDTO jjimDTO);

    JjimDTO insertJjim(JjimDTO jjimDTO);


    Set<JjimDTO> getJjimList();

    Set<InfoBoardDTO> getJjimListWithPaging(HashMap<String, Object>map);

    int getJjimTotal();

    Set<RatingsDTO> getRatingsList();


    // ratings 테이블
    boolean insertRating(RatingsDTO ratingsDTO);

    RatingsDTO getAvgByBoard(Long board_id);

    boolean updateTotalRate(InfoBoardDTO infoBoardDTO);

    boolean isExistRating(RatingsDTO ratingsDTO);
}
