package com.hlkj.domain.user.mapper;

import com.hlkj.common.mapper.MyMapper;
import com.hlkj.domain.user.pojo.User;
import org.apache.ibatis.annotations.Param;
//import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lixiangping
 * @createTime 2022年10月17日 16:51
 * @decription:
 */
public interface UserMapper extends MyMapper<User> {

    //fixme 注意搜索应该划分到主搜模块
    List<User> list();

    User getDetail(@Param("id") Long id);

    User login(@Param("username")String username, @Param("password") String password);

}