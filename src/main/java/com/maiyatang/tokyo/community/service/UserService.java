package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.GithubUser;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import java.util.UUID;

/**
 * user service
 */
@Service
public class UserService {
    @Autowired(required=false)
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        // user存在かないか判断
        User oldUser = userMapper.findByAccountId(user.getAccountId());
        if(oldUser!=null){
            oldUser.setToken(user.getToken());
            oldUser.setModifiedTime(System.currentTimeMillis());
            // ユーザー個人情報を更新
            userMapper.updateGithubUser(oldUser);
        }else{
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            // ユーザー個人情報を挿入
            userMapper.insertGithubUser(user);
        }
    }
}
