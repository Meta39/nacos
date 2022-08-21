package com.jw.rabbtimq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 *  发布订阅高级  回调
 */
@Component
public class MyCallback implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback {
    private static final Logger log = LoggerFactory.getLogger(MyCallback.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        //注入
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }


    /**
     * 交换机确认回调方法
     * 1.发消息    交换机接收到了 回调
     * @param correlationData 保存回调消息的ID及相关消息
     * @param ack 交换机是否成功收到消息
     * @param cause 交换成功机收到消息则为null，失败则为原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id =correlationData != null ?correlationData.getId() :"";
        if (ack){
            log.info("交换机成功接收ID：{}的消息",id);
        }else {
            log.info("交换机未收到ID：{}的消息，原因是：{}",id,cause);
        }
    }

    //可以在当消息传递过程中不可达到目的地时将消息返回给生产者  (交换机发送消息到queue队列失败时才会调用)
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息：{},被交换机{}退回，退回原因：{}，路由key：{}",new String(returnedMessage.getMessage().getBody(), StandardCharsets.UTF_8),returnedMessage.getExchange(),returnedMessage.getReplyText(),returnedMessage.getRoutingKey());
    }


}
