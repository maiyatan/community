package com.maiyatang.tokyo.community.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long createTime;
    private Long modifiedTime;
    private String avatarUrl;

}
