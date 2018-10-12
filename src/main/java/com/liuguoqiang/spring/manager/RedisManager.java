/**
 * BEYONDSOFT.COM INC
 */
package com.liuguoqiang.spring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liusk
 * @version $Id: RedisManager.java, v 0.1 2018/9/19 16:04 liusk Exp $
 */
@Component
public class RedisManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="stringRedisTemplate")
    private ValueOperations<String,String> valOpsStr;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object,Object> valOpsObj;

    @Resource(name = "redisTemplate")
    private HashOperations hashOpsObj;

    /**
     * 根据指定key获取string
     * @param key
     * @return
     */
    public String getStr(String key){
        return valOpsStr.get(key);
    }

    /**
     * 设置string缓存
     * @param key
     * @param value
     */
    public void setStr(String key,String value){
        valOpsStr.set(key,value);
    }

    /**
     * 删除指定key
     * @param key
     */
    public void delKey(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * redis set map
     * @param key
     * @param mk
     * @param mv
     */
    public void setMap(String key,String mk,Object mv){
        hashOpsObj.put(key,mk,mv);
    }

    /**
     * redis中获取map数据
     * @param key
     * @param mk
     * @return
     */
    public Object getMap(String key,String mk){
        return hashOpsObj.get(key,mk);
    }

}
