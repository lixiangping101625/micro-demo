package com.hlkj.domain.item.service;

import com.hlkj.sharedpojo.pojo.UnifyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 11:03
 * @decription:
 */
@RequestMapping("item-api")
public interface ItemService {

    @GetMapping("listAll")
    public UnifyResponse listAll();

    @GetMapping("info")
    public UnifyResponse detail(@RequestParam("id") Long id);

}
