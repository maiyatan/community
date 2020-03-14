package com.maiyatang.tokyo.community.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.awt.*;

@Data
public class TucaoText {
    private Integer textId;
    private Integer creator;
    @NotNull(message = "title is null")
    private String title;
    @NotNull(message = "description is null")
    private String description;
    private String tag;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private Long createTime;
    private Long modifiedTime;
}
