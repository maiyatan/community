package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TocaoTextMapper {

    // insert the text
    @Insert("insert into TOCAO_Text(creator,title,description,tag,create_time,modified_time) values " +
            "(#{creator},#{title},#{description},#{tag},#{createTime},#{modifiedTime})")
    void insertTocaoText(TucaoText text);
}
