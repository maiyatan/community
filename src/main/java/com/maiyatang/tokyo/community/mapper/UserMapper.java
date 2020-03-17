package com.maiyatang.tokyo.community.mapper;

import com.maiyatang.tokyo.community.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findByUserId(@Param("id") Integer id);

    @Select("SELECT * FROM user WHERE account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    // GithubUserユーザー個人情報を挿入
    @Insert("insert into user(account_id,name,token,create_time,modified_time,avatar_url) values " +
            "(#{accountId},#{name},#{token},#{createTime},#{modifiedTime},#{avatarUrl})")
    void insertGithubUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Update("UPDATE user SET name = #{name},token = #{token},avatar_url = #{avatarUrl},modified_time = #{modifiedTime} WHERE account_id = #{accountId}")
    void updateGithubUser(User user);
}
