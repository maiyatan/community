package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.model.TucaoText;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * publish service
 */
@Service
public class PublishService {
    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;

    /**
     * 入力情報をチャックする
     *
     * @param title       タイトル
     * @param description
     * @param tag         　タッグ
     * @param model
     */
    public Boolean checkUserInput(String title, String description, String tag, Model model) {

        //　入力情報をチャックする
        if (title == null || title == "") {
            model.addAttribute("errorMessage", "标题不能为空！");
            return false;
        } else if (description == null || description == "") {
            model.addAttribute("errorMessage", "正文不能为空！");
            return false;
        } else if (tag == null || tag == "") {
            model.addAttribute("errorMessage", "标签不能为空！");
            return false;
        }
        return true;
    }

    /**
     * 編集ツッコミ情報を取得する
     * @param textId
     * @return
     */
    public TucaoTextDTO getEditTucaoText(Integer textId) {
        TucaoText tucaoText = tucaoTextMapper.getTucaoInfoByTextId(textId);
        TucaoTextDTO tucaoTextDTO = new TucaoTextDTO();
        BeanUtils.copyProperties(tucaoText,tucaoTextDTO);
        return tucaoTextDTO;
    }

    public void createOrUpdateTucaoText(TucaoText tucaoText) {
        TucaoText tucaoInfo = tucaoTextMapper.getTucaoInfoByTextId(tucaoText.getTextId());
        if (tucaoInfo==null){
            tucaoText.setCreateTime(System.currentTimeMillis());
            tucaoText.setModifiedTime(tucaoText.getCreateTime());
            tucaoTextMapper.insertTucaoText(tucaoText);
        }else {
            tucaoText.setModifiedTime(System.currentTimeMillis());
            tucaoTextMapper.updateTucaoText(tucaoText);
        }
    }
}
