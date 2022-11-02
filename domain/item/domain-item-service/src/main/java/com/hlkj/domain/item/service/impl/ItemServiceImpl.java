package com.hlkj.domain.item.service.impl;

import com.hlkj.domain.item.mapper.ItemMapper;
import com.hlkj.domain.item.pojo.Item;
import com.hlkj.domain.item.service.ItemService;
import com.hlkj.sharedpojo.pojo.UnifyResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 11:08
 * @decription:
 */
@RestController
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    @GetMapping("listAll")
    @HystrixCommand(fallbackMethod = "listAllFail")
    public UnifyResponse listAll() {
        log.info("called listAll function");
        int i = 10/0;
        List<Item> list = itemMapper.selectAll();
        return UnifyResponse.buildSuccess(list);
    }
    /** listAll降级方法 */
    private UnifyResponse listAllFail(){
        log.error("jump in listAllFail function");
        return UnifyResponse.buildSuccess("listAll error.jump in listAllFail");
    }

    @Override
    @GetMapping("info")
    public UnifyResponse detail(@RequestParam("id") Long id) {
        return UnifyResponse.buildSuccess(itemMapper.getDetail(id));
    }
}
