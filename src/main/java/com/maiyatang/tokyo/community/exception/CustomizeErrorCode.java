package com.maiyatang.tokyo.community.exception;

public enum CustomizeErrorCode {
    NOT_FOUND_ERROR("你找的吐槽可能丢失了...");


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
