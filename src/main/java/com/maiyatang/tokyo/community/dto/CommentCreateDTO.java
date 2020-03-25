package com.maiyatang.tokyo.community.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private String comment;
    private int parentId;
    private int type;

}
