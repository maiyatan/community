package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.CommentCreateDTO;
import com.maiyatang.tokyo.community.dto.CommentDTO;
import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.service.CommentService;
import com.maiyatang.tokyo.community.service.TucaoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TucaoInfoController {
    @Autowired
    TucaoInfoService tucaoInfoService;
    @Autowired
    CommentService commentService;

    @GetMapping("/tucaoText/{textId}")
    public String hello(@PathVariable(name = "textId") Integer textId,
                        Model model) {

        TucaoTextDTO tucaoTextDTO= tucaoInfoService.getTucaoInfoByTextId(textId);

        List<CommentDTO> comments = commentService.findCommentListByTextId(textId);
        // 增加阅览数
        tucaoInfoService.incViewCount(textId);
        model.addAttribute("tucaoTextDTO", tucaoTextDTO);
        model.addAttribute("comments", comments);
        return "tucaoText";
    }
}
