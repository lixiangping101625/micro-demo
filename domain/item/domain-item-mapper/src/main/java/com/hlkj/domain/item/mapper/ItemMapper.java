package com.hlkj.domain.item.mapper;

import com.hlkj.common.mapper.MyMapper;
import com.hlkj.domain.item.pojo.Item;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月31日 10:58
 * @decription:
 */
public interface ItemMapper extends MyMapper<Item> {

    List<Item> listAll();
    Item getDetail(@Param(value = "id") Long id);

}
