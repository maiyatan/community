package com.maiyatang.tokyo.community.controller;

import com.maiyatang.tokyo.community.dto.CommentDTO;
import com.maiyatang.tokyo.community.dto.ResultDTO;
import com.maiyatang.tokyo.community.enums.ResultCodeEnum;
import com.maiyatang.tokyo.community.mapper.CommentMapper;
import com.maiyatang.tokyo.community.model.Comment;
import com.maiyatang.tokyo.community.model.User;
import com.maiyatang.tokyo.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private ResultDTO resultDTO;
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object postComment(@RequestBody CommentDTO commentDTO,
                              Model model,
                              HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        if (user==null){
            resultDTO.errorOf(ResultCodeEnum.NOT_LOGIN.getCode(),ResultCodeEnum.NOT_LOGIN.getMessage());
        }

        Comment comment = new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
//        comment.setCommentator(user.getId());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setModifiedTime(comment.getCreateTime());
        //update comment table
        commentService.insert(comment);


        return null;
    }
}
