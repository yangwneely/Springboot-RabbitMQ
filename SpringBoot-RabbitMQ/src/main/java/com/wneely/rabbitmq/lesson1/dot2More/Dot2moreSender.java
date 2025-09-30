package com.wneely.rabbitmq.lesson1.dot2More;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 点到多队列 routingKey要与QueueName相同，没有设置channel、exchange，channel会自己自己创建，默认为""，直连交换机
 */
@Slf4j
@Component("dot2moreSender")
public class Dot2moreSender {

    @Autowired
    @Qualifier(value = "rabbitTemplateDot2dmore")
    private RabbitTemplate rabbitTemplateDot2dmore;
//    private AmqpTemplate rabbitTemplateDot2dmore;

    public void sendMessage(String queueName, String message) {

        int i = 100;
        while (i > 0) {
            JSONObject jsonObject = JSON.parseObject(message);
            jsonObject.put("id", i);
            String jsonString = jsonObject.toJSONString();

            rabbitTemplateDot2dmore.convertAndSend(queueName,jsonString,
                    m -> {
                        m.getMessageProperties().setContentType("application/json");
                        m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        return m;
                    });
            log.info("-----> 发送完成消息完成，消息队列名称为{}，发送的消息是{}", queueName, jsonString);
            i--;
        }
    }
}
