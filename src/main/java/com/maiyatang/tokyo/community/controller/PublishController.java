package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.common.CookieCnt;
import com.maiyatang.tokyo.community.mapper.TocaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class PublishController {
    @Autowired(required = false)
    TocaoTextMapper toCaotext;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    PublishService publishService;

    @GetMapping("/publish")
    public String publishGet() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publishPost(@Valid @RequestParam(value = "title") String title,
                              @Valid @RequestParam(value = "description") String description,
                              @RequestParam(value = "tag") String tag,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        // user情報を取得する
        User user = CookieCnt.getUserInfoByToken(request, userMapper);
        if (user != null) {
            request.getSession().setAttribute("user",user);
        }
        // 画面を利用するため、値を渡す
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        // ユーザー個人情報をチャックする
        if (!publishService.checkUserInput(title, description, tag, model, user)){
            return "publish";
        }

        // 文章の情報をセットする
        TucaoText tucaoText = new TucaoText();
        tucaoText.setCreator(user.getId());
        tucaoText.setTitle(title);
        tucaoText.setDescription(description);
        tucaoText.setTag(tag);
        tucaoText.setCreateTime(System.currentTimeMillis());
        tucaoText.setModifiedTime(tucaoText.getCreateTime());

        toCaotext.insertTocaoText(tucaoText);

        return "redirect:/";
    }
}
