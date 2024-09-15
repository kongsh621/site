package com.example.project01.service;

import com.example.project01.domain.Criteria;
import com.example.project01.domain.InfoBoardDTO;
import com.example.project01.domain.JjimDTO;
import com.example.project01.domain.RatingsDTO;
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
    public List<InfoBoardDTO> getList(Criteria criteria) {
        return mapper.getList();
    }

    @Override
    public List<InfoBoardDTO> getRecommendList() {
        return mapper.getRecommendList();
    }

    @Override
    public List<InfoBoardDTO> getListByTitle(String title) {
        return mapper.getListByTitle(title);
    }

    @Override
    public List<InfoBoardDTO> getListWithPaging(Criteria criteria) {
        return mapper.getListWithPaging(criteria);
    }

    @Override
    public InfoBoardDTO readById(Long id) {
        return mapper.readById(id);
    }

    @Override
    public boolean write(InfoBoardDTO infoBoardDTO) {
        return mapper.insert(infoBoardDTO) == 1;
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
    public List<InfoBoardDTO> searchByTitle(Map<String, Object> map) {
        return mapper.searchByTitle(map);
    }

    @Override
    public List<InfoBoardDTO> sortByType(Map<String, Object> map) {
        return mapper.sortByType(map);
    }

    @Override
    public boolean update(InfoBoardDTO infoBoardDTO) {
        return mapper.update(infoBoardDTO) == 1;
    }


    @Override
    public boolean delete(Long id) {
        return mapper.delete(id) == 1;
    }

    @Override
    public boolean isExistJjim(JjimDTO jjimDTO) {
        return mapper.isExistJjim(jjimDTO) > 0;
    }

    @Override
    public boolean changeIsLike(JjimDTO jjimDTO) {
        return mapper.changeIsLike(jjimDTO) == 1;
    }

    @Override
    public JjimDTO insertJjim(JjimDTO jjimDTO) {
        return mapper.insertJjim(jjimDTO);
    }

    @Override
    public Set<JjimDTO> getJjimList() {
        return mapper.getJjimList();
    }

    @Override
    public Set<InfoBoardDTO> getJjimListWithPaging(HashMap<String, Object> map) {
        return mapper.getJjimListWithPaging(map);
    }

    @Override
    public int getJjimTotal() {
        return mapper.getJjimTotal();
    }

    @Override
    public Set<RatingsDTO> getRatingsList() {
        return mapper.getRatingsList();
    }

    @Override
    public boolean insertRating(RatingsDTO ratingsDTO) {
        return mapper.insertRating(ratingsDTO) == 1;
    }

    @Override
    public RatingsDTO getAvgByBoard(Long board_id) {
        return mapper.getAvgByBoard(board_id);
    }

    @Override
    public boolean updateTotalRate(InfoBoardDTO infoBoardDTO){
        return mapper.updateTotalRate(infoBoardDTO) == 1;
    }

    @Override
    public boolean isExistRating(RatingsDTO ratingsDTO) {
        return mapper.isExistRating(ratingsDTO) > 0;
    }
}

