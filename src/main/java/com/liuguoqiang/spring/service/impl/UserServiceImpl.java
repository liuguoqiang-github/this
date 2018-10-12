//package com.liuguoqiang.spring.service.impl;
//
//import com.liuguoqiang.spring.dal.entity.User;
//import com.liuguoqiang.spring.dal.mapper.UserMapper;
//import com.liuguoqiang.spring.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author liuguoqiang
// * @version v 0.1 2018/9/27
// * @Description UserServiceImpl.java
// */
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public List<User> selectAll() {
//        return userMapper.selectAll();
//    }
//
//    @Override
//    public List<User> selectById(Long id) {
//        return userMapper.selectById(id);
//    }
//}
