package com.hlkj.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lixiangping
 * @createTime 2022年10月26日 11:35
 * @decription:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Account account;
    private ResponseCode responseCode;

}
