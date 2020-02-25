package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author zxp
 * @Date 2020/2/23 0:06
 */
// @EnableEurekaServer 表示开启EurekaServer服务，开启注册中心
@EnableEurekaServer
@SpringBootApplication
public class AppEureka8100 {
    public static void main(String[] args) {
        SpringApplication.run(AppEureka8100.class, args);
    }
}
