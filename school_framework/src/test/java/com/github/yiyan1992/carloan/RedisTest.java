package com.github.yiyan1992.carloan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test0(){
        stringRedisTemplate.opsForValue().set("key","key0");
        Assert.assertEquals("key0",stringRedisTemplate.opsForValue().get("key"));
    }

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("key1","key1");
        Assert.assertEquals("key1",redisTemplate.opsForValue().get("key1"));
    }
}
