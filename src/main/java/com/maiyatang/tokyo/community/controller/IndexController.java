package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.common.CookieCnt;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.IndexService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * homePage
 */
@Controller
public class IndexController {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        // user情報を取得する
        User user = CookieCnt.getUserInfoByToken(request, userMapper);
        if (user != null) {
            request.getSession().setAttribute("user",user);
        }

        // ツッコミ情報を取得する
        List<TucaoTextDTO> tucaoInfos = indexService.getTucaoInfoList();
        // user情報をTucaoTextDTOに入れる

        model.addAttribute("tucaoInfos",tucaoInfos);

        return "index";
    }
}
