package com.itmayiedu.api.order.service.impl;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.order.feign.MemberServiceFeign;
import com.itmayiedu.api.service.IOrderService;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zxp
 * @Date 2020/2/24 22:41
 */
@RestController
public class OrderServiceImpl extends BaseApiService implements IOrderService {

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @RequestMapping("/orderToMember")
    public String orderToMember(String name) {
      UserEntity userEntity=  memberServiceFeign.getMember(name);
        return userEntity==null?"没有找到该用户信息":userEntity.toString();
    }

    /**
     * 没有解决雪崩效应的方法
     * @return
     */
    @RequestMapping("/orderToMemberUserInfo")
    @Override
    public ResponseBase orderToMemberUserInfo() {
        System.out.println("进入没有服务保护机制的订单调用会员的方法");
        return memberServiceFeign.getUserInfo();
    }


    /**
     * 新增加订单调用会员服务方法
     * 解决了雪崩效应，加入了Hystrix 服务保护机制
     * fallbackMethod 指定服务降级处理的方法
     * @HystrixCommand 此注解
     *  默认开启服务隔离方式，以线程池方式
     *  默认开启服务降级方法 orderToMemberUserInfoHystrixFallBlack
     *  默认开启熔断机制
     *
     */
    @HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallBlack")
    @RequestMapping("/orderToMemberUserInfoHystrix")
    @Override
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("进入加入了hystrix熔断保护机制的订单调用会员的方法");

        return memberServiceFeign.getUserInfo();
    }

    /**
     *  orderToMemberUserInfoHystrix 方法降级以后调用的方法
     * @return
     */
    public ResponseBase orderToMemberUserInfoHystrixFallBlack() {
        System.out.println("进入订单调用会员服务,服务降级的方法");
        return setResultSuccess("返回一个友好提示: 服务降级处理，服务器繁忙，请稍后再试");
    }

    /**
     * Hystrix 第二种写法,使用类的方式
     * @return
     */
    @RequestMapping("/orderToMemberUserInfoHystrix_demo02")
    public ResponseBase orderToMemberUserInfoHystrixDemo02() {
        System.out.println("进入加入了hystrix熔断保护机制的订单调用会员的方法");

        return memberServiceFeign.getUserInfo();
    }


    @ApiOperation("获取会员相关信息")
    @ApiImplicitParam(name="name",value = "用户姓名参数",required = true)
    @PostMapping("/getOrderBySwagger")
    public  String getOrderBySwagger(String  name){
        System.out.println("name:"+name);
        return  "订单服务userName:"+name;
    }
}
