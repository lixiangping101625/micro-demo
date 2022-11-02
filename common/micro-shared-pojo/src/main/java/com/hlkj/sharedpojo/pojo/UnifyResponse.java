package com.hlkj.sharedpojo.pojo;

import java.io.Serializable;

/**
 * @author Xiang-ping li
 * @descition 定义统一响应类
 * @date 2020/5/15 0015  11:49
 */
public class UnifyResponse<T> implements Serializable {

    private Integer code;//状态码
    private String message;//消息
    private T data;//请求路径

    public UnifyResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> UnifyResponse<T> buildSuccess(T data){
        return new UnifyResponse<>(100200, "响应成功", data);
    }

    public static <T> UnifyResponse<T> buildSuccess(){
        return new UnifyResponse<>(100200, "响应成功", null);
    }
    public static <T> UnifyResponse<T> buildFailed(){
        return new UnifyResponse<>(100500, "服务器异常", null);
    }
    public static <T> UnifyResponse<T> buildFailed(String errMsg){
        return new UnifyResponse<>(100500, errMsg, null);
    }

    //constructor getter setter toString


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public UnifyResponse() {
    }

    @Override
    public String toString() {
        return "UnifyResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}