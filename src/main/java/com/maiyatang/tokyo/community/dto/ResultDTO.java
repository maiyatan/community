package com.maiyatang.tokyo.community.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(this.code);
        resultDTO.setMessage(this.message);
        return resultDTO;
    }
}
