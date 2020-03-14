package com.maiyatang.tokyo.community.common;

import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieCnt {

    /**
     * tokenからユーザー個人情報を取得
     * @param request
     * @param userMapper
     * @return ユーザー個人情報
     */
    public static User getUserInfoByToken(HttpServletRequest request,UserMapper userMapper){
        Cookie[] cookies = request.getCookies();
        // loop cookies to find the cookie's name is token
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    return userMapper.findByToken(token);
                }
            }
        }
        return null;
    }
}
