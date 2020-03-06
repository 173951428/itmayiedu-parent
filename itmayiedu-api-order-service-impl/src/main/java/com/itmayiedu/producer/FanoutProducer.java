package com.itmayiedu.producer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * @Author zxp
 * @Date 2020/3/6 20:34
 */
@Configuration
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String queueName) {
        String msg = "my_fanout_msg:" + new Date();
        System.out.println(msg + ":" + msg);
        amqpTemplate.convertAndSend(queueName, msg);
    }

}
