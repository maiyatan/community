package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * publish service
 */
@Service
public class PublishService {

    /**
     * ユーザー個人情報をチャックする
     * @param title タイトル
     * @param description　
     * @param tag　タッグ
     * @param model　
     * @param user　ユーザー
     */
    public Boolean checkUserInput(String title, String description, String tag, Model model, User user) {
        // userがnullの判断
        if (user == null) {
            model.addAttribute("errorMessage", "用户未登录！");
            return false;
        }
        //　入力情報をチャックする
        if (title == null||title=="") {
            model.addAttribute("errorMessage", "标题不能为空！");
            return false;
        } else if (description == null||description=="") {
            model.addAttribute("errorMessage", "正文不能为空！");
            return false;
        }else if (tag == null||tag==""){
            model.addAttribute("errorMessage", "标签不能为空！");
            return false;
        }
        return true;
    }

}
