package com.maiyatang.tokyo.community.service;

import com.maiyatang.tokyo.community.dto.CommentDTO;
import com.maiyatang.tokyo.community.enums.CommentTypeEnum;
import com.maiyatang.tokyo.community.exception.CustomizeErrorCode;
import com.maiyatang.tokyo.community.exception.CustomizeException;
import com.maiyatang.tokyo.community.mapper.CommentMapper;
import com.maiyatang.tokyo.community.mapper.TucaoTextExtMapper;
import com.maiyatang.tokyo.community.mapper.TucaoTextMapper;
import com.maiyatang.tokyo.community.mapper.UserMapper;
import com.maiyatang.tokyo.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * comment service
 */
@Service
public class CommentService {
    @Autowired(required = false)
    TucaoTextMapper tucaoTextMapper;
    @Autowired(required = false)
    CommentMapper commentMapper;
    @Autowired(required = false)
    TucaoTextExtMapper tucaoTextExtMapper;
    @Autowired(required = false)
    UserMapper userMapper;

    /**
     * コメントを追加
     *
     * @param comment
     */
    @Transactional
    public void insert(Comment comment) {
        // 是否选择问题的check
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        // 评论种类是否存在
        if (comment.getType() == null || !CommentTypeEnum.isExit(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_ERROR);
        }


        if (comment.getType() == CommentTypeEnum.TUCAO.getType()) {
            // 回复问题
            TucaoText tucaoText = tucaoTextMapper.selectByPrimaryKey(comment.getParentId());
            if (tucaoText == null) {
                throw new CustomizeException(CustomizeErrorCode.NOT_FOUND_ERROR);
            }
            commentMapper.insert(comment);
            // update comment count
            incCommentCount(comment.getParentId());

        } else if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND_ERROR);
            }
            commentMapper.insert(comment);
        }
    }

    /**
     * increment comment_count
     *
     * @param textId
     */
    public void incCommentCount(Integer textId) {
        TucaoText tucoText = new TucaoText();
        tucoText.setTextId(textId);
        tucoText.setCommentCount(1);
        //コメント数を上げる
        tucaoTextExtMapper.incCommentCount(tucoText);
        //TODO 更新的阅览数总是少一个，不是最新
    }

    /**
     * @return
     */
    public List<CommentDTO> findCommentListByTextIdAndType(Integer textId, Integer type) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(textId)
                .andTypeEqualTo(type);
        commentExample.setOrderByClause("create_time desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.isEmpty()) {
            return new ArrayList<>();
        }

        // 获取去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());

        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        // 获取评论人，从转换成map（userId ： user）
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // comment转换成commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());


        return commentDTOS;
    }
}
