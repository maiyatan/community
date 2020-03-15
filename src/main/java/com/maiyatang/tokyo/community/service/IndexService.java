package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.TocaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * publish service
 */
@Service
public class IndexService {
    @Autowired(required = false)
    TocaoTextMapper tocaoTextMapper;

    @Autowired(required = false)
    UserMapper userMapper;

    public List<TucaoTextDTO> getTucaoInfoList() {
        List<TucaoText> tucaoInfoList = tocaoTextMapper.getTucaoInfo();
        List<TucaoTextDTO> tucaoInfDtoList = new ArrayList<TucaoTextDTO>();
        for (TucaoText tucaoInfo:tucaoInfoList) {
            TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
            BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
            // userを取得する
            Integer creator = tucaoInfo.getCreator();
            User user = userMapper.findByUserId(creator);
            tucaoTextDto.setUser(user);
            // tucaoInfDtoListに追加する
            tucaoInfDtoList.add(tucaoTextDto);
        }

        return tucaoInfDtoList;
    }

}
