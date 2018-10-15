/**
 * BEYONDSOFT.COM INC
 */
package com.liuguoqiang.spring.service;

import com.liuguoqiang.spring.manager.RedisManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liusk
 * @version $Id: RedisManagerTest.java, v 0.1 2018/9/18 17:56 liusk Exp $
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisManagerTest {

    @Autowired
    private RedisManager redisManager;

    @Test
    public void setTest() {
        redisManager.setStr("124445", "3455");
    }

    @Test
    public void setTest1() {
        redisManager.setStr("111", "3455");
    }

    @Test
    public void setMapTest() {
        redisManager.setMap("test-map-1", "a1", "qqq");
    }

    @Test
    public void getMapTest() {
        System.out.println("-------------------------------------" + redisManager.getMap("test-map-1", "a1"));
        Assert.assertNotNull("redis查询为空", redisManager.getMap("test-map-1", "a1"));
        // Assert.assertEquals("结果不正确",alarmDO.getDeviceName(),"SDFSDSDFSDF");
    }

}