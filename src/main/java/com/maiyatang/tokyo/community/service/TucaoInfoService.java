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

import java.util.ArrayList;
import java.util.List;

/**
 * publish service
 */
@Service
public class TucaoInfoService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;

    /**
     * ツッコミ情報を取得する
     *
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getTucaoInfoList(Integer page, Integer size) {
        Integer offSet = size * (page - 1);
        // ツッコミ情報を取得する
        List<TucaoText> tucaoInfoList = tucaoTextMapper.getTucaoInfo(offSet, size);
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
        Integer totalCount = tucaoTextMapper.getTucaoInfoCount();
        // page分ける
        paginationDTO.setPagination(page, size, totalCount);

        return paginationDTO;
    }

    /**
     * ツッコミ情報を取得する
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getMyTucaoInfoByUserId(Integer userId, Integer page, Integer size) {
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
        paginationDTO.setPagination(page, size, totalCount);

        return paginationDTO;
    }

    /**
     * ツッコミ情報の件数を取得する
     *
     * @param userId
     * @return
     */
    public Integer getTucaoInfoCountByUserId(Integer userId) {
        return tucaoTextMapper.getTucaoInfoCountByUserId(userId);
    }

    public TucaoTextDTO getTucaoInfoByTextId(Integer textId) {
        TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
        TucaoText tucaoInfo = tucaoTextMapper.getTucaoInfoByTextId(textId);
        BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
        User user = userMapper.findByUserId(tucaoInfo.getCreator());
        tucaoTextDto.setUser(user);
        return tucaoTextDto;
    }
}
