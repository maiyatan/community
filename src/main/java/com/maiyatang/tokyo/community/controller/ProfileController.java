package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.PaginationDTO;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * ProfileController
 */
@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;


    @GetMapping("/profile/{action}")
    public String hello(@PathVariable(name = "action") String action,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "5") Integer size,
                        Model model,
                        HttpServletRequest request) {

        // user情報を取得する
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getId();
        PaginationDTO paginationDTO = new PaginationDTO();
        // myTucao
        if ("myTucao".equals(action)) {
            model.addAttribute("section", "myTucao");
            model.addAttribute("sectionName", "我的吐槽");
            //　useridからツッコミ情報を取得する
            paginationDTO = profileService.getMyTucaoInfo(userId, page, size);
        }
        // myMessage
        if ("myMessage".equals(action)) {
            model.addAttribute("section", "myMessage");
            model.addAttribute("sectionName", "我的消息");
        }

        // myThumbUp
        if ("myThumbUp".equals(action)) {
            model.addAttribute("section", "myThumbUp");
            model.addAttribute("sectionName", "我的点赞");
        }
        model.addAttribute("paginationDTO", paginationDTO);
        // ツッコミ情報件数
        Integer myTucaoCount = profileService.getTucaoInfoCountByUserId(userId);
        model.addAttribute("myTucaoCount", myTucaoCount);

        return "profile";
    }
}
