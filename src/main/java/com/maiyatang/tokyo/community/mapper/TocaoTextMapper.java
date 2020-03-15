package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TocaoTextMapper {

    // insert the text
    @Insert("insert into TOCAO_Text(creator,title,description,tag,create_time,modified_time) values " +
            "(#{creator},#{title},#{description},#{tag},#{createTime},#{modifiedTime})")
    void insertTocaoText(TucaoText text);

    // ツッコミ情報を取得する
    @Select("select * from TOCAO_Text limit #{offSet},#{size} ")
    List<TucaoText> getTucaoInfo(@Param(value = "offSet") Integer page,@Param(value = "size")Integer size);
    // ツッコミ情報の件数を取得する
    @Select("SELECT COUNT(1) FROM TOCAO_Text;")
    Integer getTucaoInfoCount();
}
