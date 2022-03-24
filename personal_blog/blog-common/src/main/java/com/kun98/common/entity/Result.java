package com.kun98.common.entity;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LIU JIANKUN
 * @create 2022-01-27 23:45
 */
@ToString
@Data
public class Result {

    /**
     * flag: success | fail
     */
    private boolean flag;

    /**
     * code: like 200, 404
     * or token while login
     */
    private String code;

    /**
     * message about the result
     */
    private String message;

    Object data;

    public static Result success(){
        Result result = new Result();
        result.flag = true;
        return result;
    }
    public static Result fail(){
        Result result = new Result();
        result.flag = false;
        return result;
    }

    public Result code(String code){
        this.setCode(code);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result codeAndMessage(String code, String message){
        this.setCode(code);
        this.setMessage(message);
        return this;
    }
    public Result codeAndMessage(ResultInfo resultInfo){
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        return this;
    }

    public Result data(Object data){
        this.data = data;
        return this;
    }


    public Result(boolean flag, String code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Result() {
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean status) {
        this.flag = status;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
