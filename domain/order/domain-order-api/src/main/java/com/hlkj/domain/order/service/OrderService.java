package com.hlkj.domain.order.service;

import com.hlkj.domain.order.pojo.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 20:19
 * @decription:
 */
@FeignClient("domain-order-service")
@RequestMapping("order-api")
public interface OrderService {

    @GetMapping("listAll")
    List<Order> listAll();

    @GetMapping("detail")
    Order detail(@RequestParam("id") Long id);

}
