package com.maiyatang.tokyo.community.dto;

import com.maiyatang.tokyo.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Integer commentId;

    private String comment;

    private Integer parentId;

    private Integer type;

    private Integer commentator;

    private Long createTime;

    private Long modifiedTime;

    private Long likeCount;
    private User user;

}
