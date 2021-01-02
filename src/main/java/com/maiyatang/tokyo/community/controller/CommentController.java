package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.CommentCreateDTO;
import com.maiyatang.tokyo.community.dto.CommentDTO;
import com.maiyatang.tokyo.community.dto.ResultDTO;
import com.maiyatang.tokyo.community.enums.CommentTypeEnum;
import com.maiyatang.tokyo.community.enums.ResultCodeEnum;
import com.maiyatang.tokyo.community.exception.CustomizeErrorCode;
import com.maiyatang.tokyo.community.model.Comment;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 一级评论
     *
     * @param commentDTO
     * @param model
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentCreateDTO commentDTO,
                              Model model,
                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        // ログインしたかをチェックする
        if (user == null) {
            return ResultDTO.errorOf(ResultCodeEnum.NOT_LOGIN.getCode(), ResultCodeEnum.NOT_LOGIN.getMessage());
        }
        // コメント内容をチャックする
        if (StringUtils.isBlank(commentDTO.getComment()) || commentDTO == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_NULL_ERROR.getErrorCode(), CustomizeErrorCode.COMMENT_NULL_ERROR.getMessage());
        }

        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setCommentator(user.getId());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setModifiedTime(comment.getCreateTime());
        //update comment table
        commentService.insert(comment);


        return ResultDTO.successOf();
    }


    /**
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List> comments(@PathVariable(name = "id") Integer id) {

        List<CommentDTO> commentDTOS = commentService.findCommentListByTextIdAndType(id, CommentTypeEnum.COMMENT.getType());

        return ResultDTO.successOf(commentDTOS);
    }
}
