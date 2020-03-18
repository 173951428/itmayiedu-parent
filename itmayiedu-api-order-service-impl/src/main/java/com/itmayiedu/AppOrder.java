package com.itmayiedu;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zxp
 * @Date 2020/2/25 0:01
 */
// LCN 客户端注解
@EnableDistributedTransaction
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
// 表示启动生成swagger2的文档
@EnableSwagger2Doc
@Api("订单服务接口")
public class AppOrder {
    public static void main(String[] args) {
        SpringApplication.run(AppOrder.class,args);
    }
}
