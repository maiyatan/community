package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {
    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    PublishService publishService;

    /**
     * ツッコミ情報を編集
     * @param textId
     * @param model
     * @return
     */
    @GetMapping("/publish/{textId}")
    public String edit(@PathVariable(value = "textId") Integer textId,
                       Model model) {
        TucaoTextDTO tucaoTextDTO= publishService.getEditTucaoText(textId);
        // 画面を利用するため、値を渡す
        model.addAttribute("title", tucaoTextDTO.getTitle());
        model.addAttribute("description", tucaoTextDTO.getDescription());
        model.addAttribute("tag", tucaoTextDTO.getTag());
        model.addAttribute("textId",tucaoTextDTO.getTextId());
        return "publish";
    }
    @GetMapping("/publish")
    public String publish(){return "publish";}

    /**
     * ツッコミ情報を新規
     * @param title
     * @param description
     * @param tag
     * @param textId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/publish")
    public String publishPost(@RequestParam(value = "title") String title,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "tag") String tag,
                              @RequestParam(value = "textId") Integer textId,
                              HttpServletRequest request,
                              HttpServletResponse response,
                              Model model) {
        // user情報を取得する
        User user = (User)request.getSession().getAttribute("user");
        // userがnullの判断
        if (user == null) {
            return "redirect:/";
        }
        // 画面を利用するため、値を渡す
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        // ユーザー個人情報をチャックする
        if (!publishService.checkUserInput(title, description, tag, model)){
            return "publish";
        }
        // 文章の情報をセットする
        TucaoText tucaoText = new TucaoText();
        tucaoText.setCreator(user.getId());
        tucaoText.setTitle(title);
        tucaoText.setDescription(description);
        tucaoText.setTag(tag);
        tucaoText.setTextId(textId);

        publishService.createOrUpdateTucaoText(tucaoText);
//        tucaoTextMapper.insertTocaoText(tucaoText);

        return "redirect:/";
    }
}
