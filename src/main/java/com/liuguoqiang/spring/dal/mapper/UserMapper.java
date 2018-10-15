package com.liuguoqiang.spring.dal.mapper;

import com.liuguoqiang.spring.dal.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 查询所有
     */
    List<User> selectAll();

    /**
     * @param id 根据id查询用户信息
     */
    List<User> selectById(Long id);
}