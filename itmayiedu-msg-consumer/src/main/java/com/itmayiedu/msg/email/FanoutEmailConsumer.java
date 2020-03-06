package com.itmayiedu.msg.email;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author zxp
 * @Date 2020/3/6 20:47
 * 邮件消费端
 */
// RabbitListener 这个注解可以帮我们在消费者端指定绑定哪个队列,特别方便
@RabbitListener(queues = "fanout_email_queue")
@Configuration
public class FanoutEmailConsumer {
    // 这个注解表示该方法为接受MQ消息
    @RabbitHandler
    public void process(String msg) throws Exception {
        System.out.println("邮件消费者获取生产者消息msg:" + msg);
    }
}
