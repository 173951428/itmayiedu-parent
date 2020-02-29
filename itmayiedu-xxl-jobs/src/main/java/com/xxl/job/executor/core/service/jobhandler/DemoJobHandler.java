package com.xxl.job.executor.core.service.jobhandler;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @Author zxp
 * @Date 2020/2/29 2:55
 */


@Component
public class DemoJobHandler {
  @Value("${xxl.job.executor.port}")
    private String port;


    @XxlJob("testJobHandler")
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("进入测试定时任务.执行器端口号为:"+port);
        return ReturnT.SUCCESS;
    }
}
