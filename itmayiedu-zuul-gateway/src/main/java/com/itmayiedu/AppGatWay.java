package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author zxp
 * @Date 2020/2/26 21:33
 */
@SpringBootApplication
@EnableEurekaClient
// 表示开启网关代理
@EnableZuulProxy
public class AppGatWay {
    public static void main(String[] args) {
        SpringApplication.run(AppGatWay.class,args);
    }
}
