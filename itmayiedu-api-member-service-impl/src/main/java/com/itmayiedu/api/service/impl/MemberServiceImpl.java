package com.itmayiedu.api.service.impl;


import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.service.IMemberService;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zxp
 * @Date 2020/2/24 20:50
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements IMemberService {
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
}
