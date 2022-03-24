package com.kun98.common.entity;

/**
 * @author LIU JIANKUN
 * @create 2022-01-27 23:52
 */
public enum ResultInfo {
    NOT_FOUND("404","NOT FOUND"),
    SUCCESS("200","ACTION SUCCESS"),
    GLOBAL_EXCEPTION("400","GLOBAL EXCEPTION"),
    LOGIN_DENIED("401","WRONG USERNAME OR PASSWORD"),
    VERIFYCODE_UNMATCH("401","WRONG VERIFY CODE")
    ;


    private String code;
    private String message;


    ResultInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
