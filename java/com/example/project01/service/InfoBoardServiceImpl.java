package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardVO;
import com.example.project01.domain.JjimVO;
import com.example.project01.domain.RatingsVO;
import com.example.project01.mapper.InfoBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class InfoBoardServiceImpl implements InfoBoardService {
    @Autowired
    private InfoBoardMapper mapper;

    @Override
    public List<InfoBoardVO> getList(Criteria criteria) {
        return mapper.getList();
    }

    @Override
    public List<InfoBoardVO> getRecommendList() {
        return mapper.getRecommendList();
    }

    @Override
    public List<InfoBoardVO> getListByTitle(String title) {
        return mapper.getListByTitle(title);
    }

    @Override
    public List<InfoBoardVO> getListWithPaging(Criteria criteria) {
        return mapper.getListWithPaging(criteria);
    }

    @Override
    public InfoBoardVO readById(Long id) {
        return mapper.readById(id);
    }

    @Override
    public boolean write(InfoBoardVO infoBoardVO) {
        return mapper.insert(infoBoardVO) == 1;
    }

    @Override
    public int getTotal(String type) {
        return mapper.getTotal(type);
    }

    @Override
    public int getSearchTotal(String search) {
        return mapper.getSearchTotal(search);
    }

    @Override
    public List<InfoBoardVO> searchByTitle(Map<String, Object> map) {
        return mapper.searchByTitle(map);
    }

    @Override
    public List<InfoBoardVO> sortByType(Map<String, Object> map) {
        return mapper.sortByType(map);
    }

    @Override
    public boolean update(InfoBoardVO infoBoardVO) {
        return mapper.update(infoBoardVO) == 1;
    }


    @Override
    public boolean delete(Long id) {
        return mapper.delete(id) == 1;
    }

    @Override
    public boolean isExistJjim(JjimVO jjimVO) {
        return mapper.isExistJjim(jjimVO) > 0;
    }

    @Override
    public boolean changeIsLike(JjimVO jjimVO) {
        return mapper.changeIsLike(jjimVO) == 1;
    }

    @Override
    public JjimVO insertJjim(JjimVO jjimVO) {
        return mapper.insertJjim(jjimVO);
    }

    @Override
    public Set<JjimVO> getJjimList() {
        return mapper.getJjimList();
    }

    @Override
    public Set<InfoBoardVO> getJjimListWithPaging(HashMap<String, Object> map) {
        return mapper.getJjimListWithPaging(map);
    }

    @Override
    public int getJjimTotal() {
        return mapper.getJjimTotal();
    }

    @Override
    public Set<RatingsVO> getRatingsList() {
        return mapper.getRatingsList();
    }

    @Override
    public boolean insertRating(RatingsVO ratingsVO) {
        return mapper.insertRating(ratingsVO) == 1;
    }

    @Override
    public RatingsVO getAvgByBoard(Long board_id) {
        return mapper.getAvgByBoard(board_id);
    }

    @Override
    public boolean updateTotalRate(InfoBoardVO infoBoardVO){
        return mapper.updateTotalRate(infoBoardVO) == 1;
    }

    @Override
    public boolean isExistRating(RatingsVO ratingsVO) {
        return mapper.isExistRating(ratingsVO) > 0;
    }
}

