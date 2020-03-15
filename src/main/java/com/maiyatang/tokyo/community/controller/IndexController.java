package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.common.CookieCnt;
import com.maiyatang.tokyo.community.dto.PaginationDTO;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.IndexService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1") Integer page,
                        @RequestParam(value = "size",defaultValue = "5") Integer size) {

        // ツッコミ情報を取得する
        PaginationDTO paginationDTO = indexService.getTucaoInfoList(page,size);
        // user情報をTucaoTextDTOに入れる

        model.addAttribute("paginationDTO",paginationDTO);

        return "index";
    }
}
