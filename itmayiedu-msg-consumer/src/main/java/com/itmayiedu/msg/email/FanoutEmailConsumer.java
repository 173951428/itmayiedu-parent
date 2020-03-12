package com.itmayiedu.msg.email;
import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.msg.httpClientUtil.HttpClientUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author zxp
 * @Date 2020/3/6 20:47
 * 邮件消费端
 */
// RabbitListener 这个注解可以帮我们在消费者端指定绑定哪个队列,特别方便

@Component
public class FanoutEmailConsumer {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    // 这个注解表示该方法为接受MQ消息
    @RabbitHandler
    @RabbitListener(queues = "fanout_email_queue")
    public void process(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        // 获取唯一id
        try {
            // 模拟消费端业务逻辑出现异常
             int i=1/0;
            String messageId = message.getMessageProperties().getMessageId();
            String msg = new String(message.getBody(), "UTF-8");
            System.out.println("邮件消费者获取生产者消息msg:" + msg + ",消息id:" + messageId);
            JSONObject jsonObject = JSONObject.parseObject(msg);
            String email = jsonObject.getString("email");
            String emailUrl = "http://127.0.0.1:8999/sendEmail?email=" + email;
            System.out.println("邮件消费者开始调用第三方邮件服务器,emailUrl:" + emailUrl);
            JSONObject result = HttpClientUtils.httpGet(emailUrl);

            String messageIdRedis=  stringRedisTemplate.opsForValue().get("messageId");
            // 如果redis获取的消息id和当前执行的消息id一样,则返回,不执行业务逻辑代码,保证消息的幂等性
            if(messageId.equals(messageIdRedis)){
                return;
            }
            if(null==result){
                // 如果邮件接口没有调通,业务逻辑抛出异常,那么RabbitMQ就会产生重试机制
                throw new Exception("调用第三方邮件服务器接口失败!");
            }
            System.out.println("邮件消费者结束调用第三方邮件服务器成功,result:" + result + "程序执行结束");

            // 默认自动,现在设置为手动发送ACK
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            // 手动签收
            channel.basicAck(deliveryTag, false);

            stringRedisTemplate.opsForValue().set("messageId",messageId);
        } catch (Exception e) {
            e.printStackTrace();
            // 捕获异常,邮件队列丢弃消息,转发到死信队列中
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);

        }
    }
}
