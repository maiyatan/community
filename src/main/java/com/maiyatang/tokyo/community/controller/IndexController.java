package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.common.CookieCnt;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * homePage
 */
@Controller
public class IndexController {
    @Autowired(required = false)
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        User user = CookieCnt.getUserInfoByToken(request, userMapper);
        if (user != null) {
            request.getSession().setAttribute("user",user);
        }
        return "index";
    }
}
