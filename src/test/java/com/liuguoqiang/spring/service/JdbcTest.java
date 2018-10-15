package com.liuguoqiang.spring.service;

import com.liuguoqiang.spring.dal.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author liuguoqiang
 * @version v 0.1 2018/9/27
 * @Description jdbcTest.java
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcTest {

    @Autowired
    private UserService userService;

    //测试mysql数据库连接
    @Test
    public void linkTest() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "qqq369");//连接数据库
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(conn);
    }

    // 测试数据查询测试
    @Test
    public void selectTest() {
        try {
            List<User> list = userService.selectAll();
            list.forEach(s -> {
                System.out.println(s.getName());
            });
            List<User> list1 = userService.selectById(1L);
            System.out.println(list1.get(0).getAge() + "-----------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
