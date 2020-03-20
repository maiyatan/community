package com.maiyatang.tokyo.community.exception;

public enum CustomizeErrorCode {
    NOT_FOUND_ERROR(2001,"你找的吐槽可能丢失了..."),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何内容进行回复"),
    TYPE_NOT_FOUND(2003,"未选择任何内容进行回复"),
    COMMENT_NOT_FOUND_ERROR(2004,"评论不存在")
    ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    CustomizeErrorCode(Integer errorCode,String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    private String message;
    private Integer errorCode;
}
