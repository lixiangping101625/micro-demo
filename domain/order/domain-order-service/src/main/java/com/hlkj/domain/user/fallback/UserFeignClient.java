package com.hlkj.domain.user.fallback;

import com.hlkj.domain.user.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 23:25
 * @decription:
 */
@FeignClient(value = "domain-user-service", fallback = UserFallback.class)
public interface UserFeignClient extends UserService {
}
