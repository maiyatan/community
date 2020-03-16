package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.PaginationDTO;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
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
public class ProfileService {

    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;
    @Autowired(required = false)
    UserMapper userMapper;

    /**
     * ツッコミ情報を取得する
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getMyTucaoInfo(Integer userId, Integer page, Integer size) {
        Integer offSet = size * (page - 1);
        List<TucaoText> tucaoInfoList = tucaoTextMapper.getTucaoInfoByUserId(userId, offSet, size);
        List<TucaoTextDTO> tucaoInfDtoList = new ArrayList<TucaoTextDTO>();

        for (TucaoText tucaoInfo : tucaoInfoList) {
            TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
            BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
            // userを取得する
            Integer creator = tucaoInfo.getCreator();
            User user = userMapper.findByUserId(creator);
            tucaoTextDto.setUser(user);
            // tucaoInfDtoListに追加する
            tucaoInfDtoList.add(tucaoTextDto);
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setTucaoInfo(tucaoInfDtoList);
        // ツッコミ情報の件数を取得する
        Integer totalCount = tucaoTextMapper.getTucaoInfoCountByUserId(userId);
        // page分ける
        paginationDTO.setPagination(page,size,totalCount);

        return paginationDTO;
    }

    /**
     * ツッコミ情報の件数を取得する
     * @param userId
     * @return
     */
    public Integer getTucaoInfoCountByUserId(Integer userId) {
        return tucaoTextMapper.getTucaoInfoCountByUserId(userId);
    }
}
