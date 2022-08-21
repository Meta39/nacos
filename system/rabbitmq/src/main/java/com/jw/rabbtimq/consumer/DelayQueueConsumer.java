package com.jw.rabbtimq.consumer;

import com.jw.rabbtimq.config.DelayedQueueConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 消费者  基于插件的延迟消息   解决先后时间先后问题
 * 前提：需要在服务器安装延迟队列rabbitmq_delayed_message_exchange插件
 */
@Component
public class DelayQueueConsumer {
    private static final Logger log = LoggerFactory.getLogger(DelayQueueConsumer.class);
    //监听消息
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("当前时间：{}，收到延迟队列的消息：{}",new Date().toString(),msg);
    }
}
