package com.maiyatang.tokyo.community.enums;

public enum  ResultCodeEnum {
    NOT_LOGIN(2001,"未登录，不能进行评论。")
    ;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
