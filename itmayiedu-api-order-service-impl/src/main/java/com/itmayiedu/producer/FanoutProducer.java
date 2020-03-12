package com.itmayiedu.producer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * @Author zxp
 * @Date 2020/3/6 20:34
 * 模拟RabbitMQ 生产者
 */
@Component
public class FanoutProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     *
     * @param queueName 生产者端需要发送给队列的名字
     */
    public void send(String queueName) {
       JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "173951428");
        jsonObject.put("timestamp", System.currentTimeMillis());
        String jsonString = jsonObject.toJSONString();
        System.out.println("生产者端发送的消息为:"+jsonString);

        Message message = MessageBuilder.withBody(jsonString.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setContentEncoding("utf-8")
                // 利用UUID,产生唯一id
                .setMessageId(UUID.randomUUID() + "").build();


        // 发送消息到RabbitMQ
        amqpTemplate.convertAndSend(queueName, message);


    }

}
