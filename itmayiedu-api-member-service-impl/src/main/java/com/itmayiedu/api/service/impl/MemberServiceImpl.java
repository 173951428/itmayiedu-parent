package com.itmayiedu.api.service.impl;


import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.itmayiedu.api.dao.UserEntityMapper;
import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.service.IMemberService;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zxp
 * @Date 2020/2/24 20:50
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {

    @Autowired
    UserEntityMapper mapper;

    @RequestMapping("/getMember")
    @Override
    public UserEntity getMember(@RequestParam("name") String name) {
        UserEntity userEntity=new UserEntity();
        userEntity.setName(name);
        userEntity.setAge(20);
        return userEntity;
    }

    /**
     * 会员服务获取用户详情
     * @return
     */
    @RequestMapping("/getUserInfo")
    @Override
    public ResponseBase getUserInfo() {
       try {
            // 会员接口服务产生1500 毫秒延迟
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setResultSuccess("订单接口调用会员服务接口成功。。。。。");
    }


    @LcnTransaction(propagation = DTXPropagation.SUPPORTS)// 被调用方分布式事务注解
    @Transactional
    @RequestMapping("/insertUser")
    public int insertUser(String name, int age) {
        return mapper.insertUser(name,age);
    }

    @ApiOperation("swagger会员服务接口类测试")
    @ApiImplicitParam(name="name",value = "用户姓名参数",required = true,dataType = "String")
    @PostMapping("/getMemberBySwagger")
    public  String getMemberBySwagger(String  name){
        System.out.println("name:"+name);
        return  "会员服务userName:"+name;
    }
}
