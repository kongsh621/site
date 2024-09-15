package com.example.project01.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private boolean success;
    private String action;
    private InfoBoardDTO result;

    public ResultDTO(boolean success, String action){
        this(success, action, null);
    }

}
