package com.example.project01.mapper;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardVO;
import com.example.project01.domain.JjimVO;
import com.example.project01.domain.RatingsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface InfoBoardMapper {
    // 리스트
    List<InfoBoardVO> getList();

    List<InfoBoardVO> getRecommendList();
    // 제목으로 검색 - 중복이네
    List<InfoBoardVO> getListByTitle(String title);

    List<InfoBoardVO> getListWithPaging(Criteria criteria);

    InfoBoardVO readById(Long id);

    int insert(InfoBoardVO infoBoardVO);

    int getTotal(String type);

    List<InfoBoardVO> searchByTitle(Map<String, Object> map);

    List<InfoBoardVO> sortByType(Map<String, Object> map);

    int getSearchTotal(String search);

    int update(InfoBoardVO infoBoardVO);


    int delete(Long id);

    int isExistJjim(JjimVO jjimVO);

    int changeIsLike(JjimVO jjimVO);

    JjimVO insertJjim(JjimVO jjimVO);

    Set<JjimVO> getJjimList();

    Set<InfoBoardVO> getJjimListWithPaging(HashMap<String, Object>map);

    int getJjimTotal();

    Set<RatingsVO> getRatingsList();

    int insertRating(RatingsVO ratingsVO);

    RatingsVO getAvgByBoard(Long board_id);

    int updateTotalRate(InfoBoardVO infoBoardVO);

    // 관심목록 만들 때 사용
    int isExistRating(RatingsVO ratingsVO);
}
