package com.jw.rabbtimq.consumer;

import com.jw.rabbtimq.config.ConfirmConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 备份交换机
 * 报警消费者
 */
@Component
public class WarningConsumer {
    private static final Logger log = LoggerFactory.getLogger(WarningConsumer.class);

    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveWarningMsg(Message message){
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.error("报警发现不可路由消息：{}",msg);
    }

}
