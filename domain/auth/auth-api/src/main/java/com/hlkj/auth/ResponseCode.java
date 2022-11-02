package com.hlkj.auth;

import lombok.Data;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:33
 * @decription: 访问过程中产生的异常
 */
public enum  ResponseCode {

    SUCCESS(1L, "success"),
    INCORRECT_PWD(1000L, "incorrect password"),
    USER_NOT_FOUND(1001L, "user not found"),
    INVALID_TOKEN(1002L, "invalid token");

    private Long code;
    private String msg;

    ResponseCode(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
