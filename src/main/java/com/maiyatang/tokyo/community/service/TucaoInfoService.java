package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.PaginationDTO;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.exception.CustomizeErrorCode;
import com.maiyatang.tokyo.community.exception.CustomizeException;
import com.maiyatang.tokyo.community.mapper.TucaoTextExtMapper;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.TucaoTextExample;
import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired(required = false)
    TucaoTextExtMapper tucaoTextExtMapper;

    /**
     * ツッコミ情報を取得する
     *
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO getTucaoInfoList(Integer page, Integer size) {
        Integer offSet = size * (page - 1);
//        List<TucaoText> tucaoInfoList = tucaoTextMapper.getTucaoInfo(offSet, size);
        // ツッコミ情報を取得する
        TucaoTextExample tucaoTextExample = new TucaoTextExample();
        tucaoTextExample.setOrderByClause("create_time desc");
        List<TucaoText> tucaoInfoList = tucaoTextMapper.selectByExampleWithRowbounds(tucaoTextExample, new RowBounds(offSet, size));
        List<TucaoTextDTO> tucaoInfDtoList = new ArrayList<TucaoTextDTO>();

        for (TucaoText tucaoInfo : tucaoInfoList) {
            TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
            BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
            // userを取得する
            Integer creator = tucaoInfo.getCreator();
            User user = userMapper.selectByPrimaryKey(creator);
            tucaoTextDto.setUser(user);
            // tucaoInfDtoListに追加する
            tucaoInfDtoList.add(tucaoTextDto);
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setTucaoInfo(tucaoInfDtoList);
        // ツッコミ情報の件数を取得する
        Integer totalCount = (int) tucaoTextMapper.countByExample(new TucaoTextExample());
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
        List<TucaoText> tucaoInfoList = tucaoTextMapper.selectByExampleWithRowbounds(new TucaoTextExample(), new RowBounds(offSet, size));
        List<TucaoTextDTO> tucaoInfDtoList = new ArrayList<TucaoTextDTO>();

        for (TucaoText tucaoInfo : tucaoInfoList) {
            TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
            BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
            // userを取得する
            Integer creator = tucaoInfo.getCreator();
            User user = userMapper.selectByPrimaryKey(creator);
            tucaoTextDto.setUser(user);
            // tucaoInfDtoListに追加する
            tucaoInfDtoList.add(tucaoTextDto);
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setTucaoInfo(tucaoInfDtoList);
        // ツッコミ情報の件数を取得する
        TucaoTextExample tucaoTextExample = new TucaoTextExample();
        tucaoTextExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) tucaoTextMapper.countByExample(tucaoTextExample);
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
        TucaoTextExample tucaoTextExample = new TucaoTextExample();
        tucaoTextExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) tucaoTextMapper.countByExample(tucaoTextExample);
        return totalCount;
    }

    public TucaoTextDTO getTucaoInfoByTextId(Integer textId) {
        TucaoTextDTO tucaoTextDto = new TucaoTextDTO();
        TucaoTextExample tucaoTextExample = new TucaoTextExample();
        tucaoTextExample.createCriteria().andTextIdEqualTo(textId);
//        TucaoText tucaoInfo = tucaoTextMapper.selectByPrimaryKey(textId);
        // textidで内容があるかを判断
        long count = tucaoTextMapper.countByExample(tucaoTextExample);
        if (count==0){
            throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_ERROR);
        }
        // 更新したツッコミ情報を取得
        TucaoText tucaoInfo = tucaoTextMapper.selectByPrimaryKey(textId);
        BeanUtils.copyProperties(tucaoInfo, tucaoTextDto);
        User user = userMapper.selectByPrimaryKey(tucaoInfo.getCreator());
        tucaoTextDto.setUser(user);
        return tucaoTextDto;
    }

    public void incViewCount(Integer textId){
        TucaoText tucoText = new TucaoText();
        tucoText.setTextId(textId);
        tucoText.setViewCount(1);
        //閲覧数を上げる
        tucaoTextExtMapper.incViewCount(tucoText);
        TucaoText tucaoInfo = tucaoTextMapper.selectByPrimaryKey(textId);
        //TODO 更新的阅览数总是少一个，不是最新
    }
}
