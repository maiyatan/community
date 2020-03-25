package com.maiyatang.tokyo.community.exception;

public class CustomizeException extends RuntimeException {
    Integer code;
    String message;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
