package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findByState(@Param("id") String id);

    // GithubUserユーザー個人情報を挿入
    @Insert("insert into user(account_id,name,token,create_time,modified_time,avatar_url) values " +
            "(#{accountId},#{name},#{token},#{createTime},#{modifiedTime},#{avatarUrl})")
    void insertGithubUser (User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
}
