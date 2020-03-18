package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zxp
 * @Date 2020/3/18 19:16
 * 在集群环境下,利用redis,生成全局ID
 */
@RestController
public class OrderIdByRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 利用Redis获取订单ID
     * @param key
     * @return 从 20200318192616-00001开始,依次递增返回的订单号码
     */
    @RequestMapping("/getOrderId")
    public String order(String key) {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());

            long incrementAndGet = redisAtomicLong.incrementAndGet();
            // %1$05d 表示初始化值为1,长度为5,数字为00001开始的订单号码
            String orderId = prefix() + "-" + String.format("%1$05d", incrementAndGet);

         return orderId;
    }

    /**
     *
     * @return  返回年月日时分秒
     */
    public static String prefix() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        temp_str = sdf.format(dt);
        return temp_str;
    }
}
