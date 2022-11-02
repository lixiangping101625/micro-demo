package com.hlkj.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 15:28
 * @decription: 公共Mapper,继承自己MyMapper。剩下mapper应该归属于不同的微服务
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
