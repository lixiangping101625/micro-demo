package com.hlkj.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:32
 * @decription:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionID = 1L;

    private Long userId;
    private String username;
    private String token;
    private String refreshToken;
    private boolean skipVerification;

}
