package com.itmayiedu.rabbitmq;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author zxp
 * @Date 2020/3/6 19:32
 * 发布订阅模式,配置交换机类型为Fanout类型
 */
@Configuration
public class FanoutConfig {
    // 邮件队列
    private String FANOUT_EMAIL_QUEUE = "fanout_email_queue";

    // 短信队列
    private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
    // 交换机
    private String EXCHANGE_NAME = "fanoutExchange";

    // 定义队列邮件
    @Bean
    public Queue fanOutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }

    // 定义短信队列
    @Bean
    public Queue fanOutSmsQueue() {
        return new Queue(FANOUT_SMS_QUEUE);
    }

    /**
     *   return FanoutExchange 这是订阅发布模式的交换机类型.如果想使用topic模式的交换机类型,只需要返回不同的交换机类型
     *   比如 TopicExchange,通配符模式交换机
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }



    /**
     *  邮件队列和交换机绑定,参数名称 fanOutEmailQueue,fanoutExchange 一定要和前面申明的bean的名字一致
     * @param fanOutEmailQueue  邮件队列名字
     * @param fanoutExchange    交换机名字
     * @return
     */
    @Bean
    Binding bindingExchangeEmail(Queue fanOutEmailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutEmailQueue).to(fanoutExchange);
    }

    /**
     * 短信队列和交换机绑定,参数名称 fanOutSmsQueue,fanoutExchange 一定要和前面申明的bean的名字一致
     * @param fanOutSmsQueue 短信队列名字
     * @param fanoutExchange 交换机名字
     * @return
     */
    @Bean
    Binding bindingExchangeSms(Queue fanOutSmsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanOutSmsQueue).to(fanoutExchange);
    }

}
