package com.jw.rabbtimq.consumer;

import com.jw.rabbtimq.config.ConfirmConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 发布订阅高级消费者    （确认消息是否成功发送）
 */
@Component
public class ConfirmConsumer {
    private static final Logger log = LoggerFactory.getLogger(ConfirmConsumer.class);

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("接收到的队列confirm.queue消息：{}",msg);
    }
}
