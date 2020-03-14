package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.common.CookieCnt;
import com.maiyatang.tokyo.community.mapper.TocaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
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
        // 画面を利用するため、値を渡す
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        // 作者のidを取得
        User user = CookieCnt.getUserInfoByToken(request, userMapper);
        // userがnullの判断
        if (user == null) {
            model.addAttribute("errorMessage", "用户未登录！");
            return "publish";
        }
        //　入力情報をチャックする
        if (title == null||title=="") {
            model.addAttribute("errorMessage", "标题不能为空！");
            return "publish";
        } else if (description == null||description=="") {
            model.addAttribute("errorMessage", "正文不能为空！");
            return "publish";
        }else if (tag == null||tag==""){
            model.addAttribute("errorMessage", "标签不能为空！");
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
