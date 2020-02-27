package com.itmayiedu;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zxp
 * @Date 2020/2/26 21:33
 */
@SpringBootApplication
@EnableEurekaClient
// 表示开启网关代理
@EnableZuulProxy
@EnableSwagger2Doc
public class AppGatWay {
    public static void main(String[] args) {
        SpringApplication.run(AppGatWay.class,args);
    }


    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider {
        @Override
        public List<SwaggerResource> get() {
            List resources = new ArrayList<>();
            // app-itmayiedu-order
            resources.add(swaggerResource("app-itmayiedu-member", "/api-member/v2/api-docs", "2.0"));
            resources.add(swaggerResource("app-itmayiedu-order", "/api-order/v2/api-docs", "2.0"));
            return resources;
        }

        private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }

}
