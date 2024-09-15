package com.example.project01.mapper;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardDTO;
import com.example.project01.domain.JjimDTO;
import com.example.project01.domain.RatingsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface InfoBoardMapper {
    // 리스트
    List<InfoBoardDTO> getList();

    List<InfoBoardDTO> getRecommendList();
    // 제목으로 검색 - 중복이네
    List<InfoBoardDTO> getListByTitle(String title);

    List<InfoBoardDTO> getListWithPaging(Criteria criteria);

    InfoBoardDTO readById(Long id);

    int insert(InfoBoardDTO infoBoardDTO);

    int getTotal(String type);

    List<InfoBoardDTO> searchByTitle(Map<String, Object> map);

    List<InfoBoardDTO> sortByType(Map<String, Object> map);

    int getSearchTotal(String search);

    int update(InfoBoardDTO infoBoardDTO);


    int delete(Long id);

    int isExistJjim(JjimDTO jjimDTO);

    int changeIsLike(JjimDTO jjimDTO);

    JjimDTO insertJjim(JjimDTO jjimDTO);

    Set<JjimDTO> getJjimList();

    Set<InfoBoardDTO> getJjimListWithPaging(HashMap<String, Object>map);

    int getJjimTotal();

    Set<RatingsDTO> getRatingsList();

    int insertRating(RatingsDTO ratingsDTO);

    RatingsDTO getAvgByBoard(Long board_id);

    int updateTotalRate(InfoBoardDTO infoBoardDTO);

    // 관심목록 만들 때 사용
    int isExistRating(RatingsDTO ratingsDTO);
}
