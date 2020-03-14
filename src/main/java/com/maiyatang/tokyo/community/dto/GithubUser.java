package com.maiyatang.tokyo.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String login;//可以代替name作为用户名
    private Long id;
    private String name;//很多人不设置这个名称，所以用login较好
    private String blog;
    private String email;
    private String accountId;
    private Long createTime;
    private Long modifiedTime;
    private String avatarUrl;

}

