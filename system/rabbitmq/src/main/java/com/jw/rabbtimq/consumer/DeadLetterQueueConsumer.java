package com.jw.rabbtimq.consumer;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 队列TTL 消费者
 */
@Component
public class DeadLetterQueueConsumer {
    private static final Logger log = LoggerFactory.getLogger(DeadLetterQueueConsumer.class);
    //接收消息
    @RabbitListener(queues = "QD")
    public void receiveD(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},接收到死信队列消息：{}",new Date().toString(),msg);
    }
}
