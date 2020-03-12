package com.itmayiedu.msg.email;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author zxp
 * @Date 2020/3/9 22:24
 * 邮件死信队列消费端
 */
@Component
public class FanoutDeadEmailConsumer {
    // 这个注解表示该方法为接受MQ消息
    @RabbitHandler
    @RabbitListener(queues = "dead_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {

        String messageId = message.getMessageProperties().getMessageId();
        String msg = new String(message.getBody(), "UTF-8");
        System.out.println("死信邮件消费者获取生产者消息msg:" + msg + ",消息id:" + messageId);

        // 默认自动,现在设置为手动发送ACK
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 手动签收
        channel.basicAck(deliveryTag, false);

    }
}
