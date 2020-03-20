package com.maiyatang.tokyo.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String comment;
    private long parentId;
    private int type;

}
