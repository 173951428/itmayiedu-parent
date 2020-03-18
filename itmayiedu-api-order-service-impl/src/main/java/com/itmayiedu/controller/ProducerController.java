package com.itmayiedu.controller;
import com.itmayiedu.producer.FanoutProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zxp
 * @Date 2020/3/6 20:37
 * 测试RabbitMQ的类
 */
@RestController
public class ProducerController {

    @Autowired
    private FanoutProducer fanoutProducer;

    /**
     *
     * @param queueName  队列的名字
     * @return
     */
    @RequestMapping("/sendFanout")
    public String sendFanout(String queueName) {
        fanoutProducer.send(queueName);
        return "success";
    }

}
