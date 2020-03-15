package com.maiyatang.tokyo.community.dto;

import com.maiyatang.tokyo.community.model.User;
import lombok.Data;

@Data
public class TucaoTextDTO {
    private Integer textId;
    private Integer creator;
    private String title;
    private String description;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Long createTime;
    private Long modifiedTime;
    private User user;

}
