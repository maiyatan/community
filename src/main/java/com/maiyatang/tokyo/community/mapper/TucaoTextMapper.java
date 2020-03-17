package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TucaoTextMapper {

    // insert the text
    @Insert("insert into TOCAO_Text(creator,title,description,tag,create_time,modified_time) values " +
            "(#{creator},#{title},#{description},#{tag},#{createTime},#{modifiedTime})")
    void insertTucaoText(TucaoText text);

    // ツッコミ情報を取得する
    @Select("select * from TOCAO_Text limit #{offSet},#{size} ")
    List<TucaoText> getTucaoInfo(@Param(value = "offSet") Integer page, @Param(value = "size") Integer size);

    // ツッコミ情報の件数を取得する
    @Select("SELECT COUNT(1) FROM TOCAO_Text;")
    Integer getTucaoInfoCount();

    // ツッコミ情報を取得する(by userId)
    @Select("select * from TOCAO_Text where creator=#{userId} limit #{offSet},#{size} ")
    List<TucaoText> getTucaoInfoByUserId(@Param(value = "userId") Integer userId, @Param(value = "offSet") Integer page, @Param(value = "size") Integer size);
    // ツッコミ情報の件数を取得する(by userId)
    @Select("SELECT COUNT(1) FROM TOCAO_Text where creator=#{userId};")
    Integer getTucaoInfoCountByUserId(@Param(value = "userId") Integer userId);

    @Select("select * from TOCAO_Text where text_id=#{textId}")
    TucaoText getTucaoInfoByTextId(Integer textId);

    @Update("UPDATE TOCAO_Text SET title = #{title},description = #{description},tag = #{tag},modified_time = #{modifiedTime} WHERE text_id = #{textId}")
    void updateTucaoText(TucaoText tucaoText);
}
