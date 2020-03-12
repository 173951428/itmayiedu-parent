package com.itmayiedu.rabbitmq;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zxp
 * @Date 2020/3/6 19:32
 * 发布订阅模式,配置交换机类型为Fanout类型
 */
@Configuration
public class FanoutConfig {

    /**
     * 定义死信队列相关信息
     */
    public final static String deadQueueName = "dead_queue";
    public final static String deadRoutingKey = "dead_routing_key";
    public final static String deadExchangeName = "dead_exchange";
    /**
     * 死信队列 交换机标识符
     */
    public static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";
    /**
     * 死信队列交换机绑定键标识符
     */
    public static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";








    // 邮件队列
    private String FANOUT_EMAIL_QUEUE = "fanout_email_queue";

    // 短信队列
    private String FANOUT_SMS_QUEUE = "fanout_sms_queue";
    // 交换机
    private String EXCHANGE_NAME = "fanoutExchange";



    // 定义队列邮件,现在要绑定死信队交换机,所以该方法注释,重新定义邮件队列
  /*  @Bean
    public Queue fanOutEmailQueue() {
        return new Queue(FANOUT_EMAIL_QUEUE);
    }*/

    /**
     * 重新定义邮件队列,并且绑定死信交换机,死信队列
     * @return
     */
    @Bean
    public Queue fanOutEmailQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put(DEAD_LETTER_QUEUE_KEY, deadExchangeName);
        args.put(DEAD_LETTER_ROUTING_KEY, deadRoutingKey);
        /**
         *  将邮件队列绑定到私信交换机,死信队列,注意:现在重新绑定私信队列和私信交换机,
         *  之前的用过的邮件队列和和交换机不能再用了,必须重新清空邮件队列和交换机,重新绑定
         *  不然会报错
         */
        Queue queue = new Queue(FANOUT_EMAIL_QUEUE, true, false, false, args);
        return queue;
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


    /**
     * 创建死信邮件交换机
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(deadExchangeName);
    }

    /**
     * 创建死信邮件队列
     * @return
     */
    @Bean
    public Queue deadQueue() {
        Queue queue = new Queue(deadQueueName, true);
        return queue;
    }


    /**
     * 邮件死信交换机绑定死信队列
     * @param deadQueue
     * @param deadExchange
     * @return
     */
    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(deadRoutingKey);
    }

}
