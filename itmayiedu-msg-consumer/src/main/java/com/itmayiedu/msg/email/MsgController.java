package com.itmayiedu.msg.email;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zxp
 * @Date 2020/3/8 13:45
 */
@RestController
public class MsgController {

    // 模拟第三方发送邮件
    @RequestMapping("/sendEmail")
    public Map<String, Object> sendEmail(String email) {
        System.out.println("开始发送邮件:" + email);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "200");
        result.put("msg", "发送邮件成功..");
        System.out.println("发送邮件成功");
        return result;
    }
}
