package com.itmayiedu.api.order.feign;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.service.IMemberService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zxp
 * @Date 2020/2/24 20:32
 * 会员接口
 */
@FeignClient(name="itmayiedu-api-member-service-impl")
/**
 * 这里设计很巧妙，让订单服务端的Feign客户端去集成了会员端的接口。可以减少重新再订单实现端去创建会员的实体类，以及重新写调用会员端的feign接口代码.
 * 说白了，就是订单服务集成会员服务接口，用来实现feign客户端的调用，减少重复代码编写
 */
public interface MemberServiceFeign extends IMemberService {

}
