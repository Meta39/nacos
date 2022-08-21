package com.jw.rabbtimq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 基于插件的延迟队列配置（需要在服务器安装延迟队列rabbitmq_delayed_message_exchange插件）
 */
@Configuration
public class DelayedQueueConfig {

    //交换机
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    //队列
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    //routingKey
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    //声明队列
    @Bean
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME);
    }

    //声明交换机 基于插件的 CustomExchange 自定义交换机
    @Bean
    public CustomExchange delayedExchange(){

        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","direct");

        /**
         * 1.交换机名称
         * 2.交换机的类型
         * 3.是否持久化
         * 4.是否自动删除
         * 5.其它参数
         */
        //type=x-delayed-message（需要在服务器安装延迟队列rabbitmq_delayed_message_exchange插件）
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",true,false,arguments);
    }

    //绑定    延迟队列和交换机
    @Bean
    public Binding delayedQueueBindingDelayedExchange(@Qualifier("delayedQueue") Queue delayedQueue, @Qualifier("delayedExchange") CustomExchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
