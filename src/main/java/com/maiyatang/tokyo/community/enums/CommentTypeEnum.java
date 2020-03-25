package com.maiyatang.tokyo.community.enums;

public enum CommentTypeEnum {
    TUCAO(1),//ツッコミ情報
    COMMENT(2)//　コメント
    ;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExit(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
