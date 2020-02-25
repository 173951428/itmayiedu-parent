package com.itmayiedu.api.service;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.base.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author zxp
 * @Date 2020/2/24 20:32
 * 会员接口
 */
public interface IMemberService {

        @RequestMapping("/getMember")
        UserEntity getMember(@RequestParam("name") String name);

        @RequestMapping("/getUserInfo")
        ResponseBase getUserInfo();
}
