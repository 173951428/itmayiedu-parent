package com.itmayiedu.api.service;

import com.itmayiedu.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zxp
 * @Date 2020/2/24 22:31
 */
public interface IOrderService {
    // 订单服务调用会员服务，feign客户端调用
      @RequestMapping("/orderToMember")
      String orderToMember(String name);

      // 订单服务接口，调用会员服务接口
      @RequestMapping("/orderToMemberUserInfo")
      ResponseBase orderToMemberUserInfo();

        @RequestMapping("/orderToMemberUserInfoHystrix")
       ResponseBase orderToMemberUserInfoHystrix();
}
