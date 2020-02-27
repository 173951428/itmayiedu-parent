package com.itmayiedu;

import com.spring4all.swagger.EnableSwagger2Doc;
import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zxp
 * @Date 2020/2/24 22:12
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
// 表示启动生成swagger2的文档
@EnableSwagger2Doc
//规定是哪个服务的接口的
@Api("会员服务接口")
public class AppMember {
    public static void main(String[] args) {
        SpringApplication.run(AppMember.class,args);
    }
}
