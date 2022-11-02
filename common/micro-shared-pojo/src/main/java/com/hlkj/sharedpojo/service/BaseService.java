package com.hlkj.sharedpojo.service;

import com.github.pagehelper.PageInfo;
import com.hlkj.sharedpojo.pojo.PageGridResult;

import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 15:48
 * @decription: 包装了一个PageGridResult，供所有上下游微服务公共使用而已
 */
public class BaseService {

    public PageGridResult setterPagedGrid(List<?> list, Integer page){
        PageInfo<?> pageList = new PageInfo<>(list);
        PageGridResult grid = new PageGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getSize());
        grid.setRecords(pageList.getTotal());
        return grid;
    }
}
