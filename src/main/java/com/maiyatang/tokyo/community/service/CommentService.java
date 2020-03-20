package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.TucaoTextDTO;
import com.maiyatang.tokyo.community.enums.CommentTypeEnum;
import com.maiyatang.tokyo.community.exception.CustomizeErrorCode;
import com.maiyatang.tokyo.community.exception.CustomizeException;
import com.maiyatang.tokyo.community.mapper.CommentMapper;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.model.Comment;
import com.maiyatang.tokyo.community.model.TucaoText;
import com.maiyatang.tokyo.community.model.TucaoTextExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * comment service
 */
@Service
public class CommentService {
    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;
    @Autowired(required = false)
    CommentMapper commentMapper;

    public void insert(Comment comment) {
        // 是否选择问题的判断
        if (comment.getParentId()==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        // 评论种类是否存在
        if (comment.getType()==null || !CommentTypeEnum.isExit(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_ERROR);
        }
        if (comment.getType()==CommentTypeEnum.QUESTION.getType()){
            // 回复问题

        }else if (comment.getType()==CommentTypeEnum.COMMENT.getType()){
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment==null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND_ERROR);
            }
            commentMapper.insert(comment);
        }

    }
}
