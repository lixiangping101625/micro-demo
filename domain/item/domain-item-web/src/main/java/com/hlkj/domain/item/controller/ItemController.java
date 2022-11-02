package com.hlkj.domain.item.controller;

import com.hlkj.domain.item.service.ItemService;
import com.hlkj.sharedpojo.pojo.UnifyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 11:16
 * @decription:
 */
@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @GetMapping("/listAll")
    public UnifyResponse listAll()
    {
        return itemService.listAll();
    }

    @GetMapping("/detail/{id}")
    public UnifyResponse detail(@PathVariable(name = "id") Long id)
    {
        return itemService.detail(id);
    }

}
