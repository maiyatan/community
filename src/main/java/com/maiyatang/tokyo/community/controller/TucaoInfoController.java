package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.service.TucaoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TucaoInfoController {
    @Autowired
    TucaoInfoService tucaoInfoService;

    @GetMapping("/tucaoText/{textId}")
    public String hello(@PathVariable(name = "textId") Integer textId,
                        Model model) {

        TucaoTextDTO tucaoTextDTO= tucaoInfoService.getTucaoInfoByTextId(textId);
        model.addAttribute("tucaoTextDTO", tucaoTextDTO);
        return "tucaoText";
    }
}
