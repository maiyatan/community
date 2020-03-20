package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.TucaoTextExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TucaoTextExtMapper {
    void incViewCount (TucaoText tucaoInfo);

    @Select("select * form tocao_text limit #{offSet} , #{size}")
    List<TucaoText> getTucaoInfoLimitPage(Integer offSet, Integer size);
}