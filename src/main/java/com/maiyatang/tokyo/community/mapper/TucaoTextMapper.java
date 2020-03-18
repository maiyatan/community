package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.TucaoTextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TucaoTextMapper {
    long countByExample(TucaoTextExample example);

    int deleteByExample(TucaoTextExample example);

    int deleteByPrimaryKey(Integer textId);

    int insert(TucaoText record);

    int insertSelective(TucaoText record);

    List<TucaoText> selectByExampleWithBLOBsWithRowbounds(TucaoTextExample example, RowBounds rowBounds);

    List<TucaoText> selectByExampleWithBLOBs(TucaoTextExample example);

    // 分页
    List<TucaoText> selectByExampleWithRowbounds(TucaoTextExample example, RowBounds rowBounds);

    List<TucaoText> selectByExample(TucaoTextExample example);

    TucaoText selectByPrimaryKey(Integer textId);

    int updateByExampleSelective(@Param("record") TucaoText record, @Param("example") TucaoTextExample example);

    int updateByExampleWithBLOBs(@Param("record") TucaoText record, @Param("example") TucaoTextExample example);

    int updateByExample(@Param("record") TucaoText record, @Param("example") TucaoTextExample example);

    int updateByPrimaryKeySelective(TucaoText record);
    int updateByPrimaryKeyWithBLOBs(TucaoText record);

    int updateByPrimaryKey(TucaoText record);
}