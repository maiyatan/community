package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user service
 */
@Service
public class UserService {
    @Autowired(required=false)
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        // user存在かないか判断
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
//        User oldUser = userMapper.findByAccountId(user.getAccountId());
        if(!users.isEmpty()){
            User oldUser =users.get(0);
            oldUser.setToken(user.getToken());
            oldUser.setModifiedTime(System.currentTimeMillis());
            // ユーザー個人情報を更新
            userMapper.updateByPrimaryKeySelective(oldUser);
        }else{
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            // ユーザー個人情報を挿入
            userMapper.insert(user);
        }
    }
}
