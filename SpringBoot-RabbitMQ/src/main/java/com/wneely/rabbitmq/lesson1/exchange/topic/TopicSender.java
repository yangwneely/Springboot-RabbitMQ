package com.wneely.rabbitmq.lesson1.exchange.topic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * topic 主体交换机
 */
@Slf4j
@Component("topicSender")
public class TopicSender {

    @Autowired
    @Qualifier(value = "rabbitTemplatetopic")
    private RabbitTemplate rabbitTemplatetopic;
//    private AmqpTemplate rabbitTemplatetopic;

    public void sendMessage(String exchange, String routingKey, String message) {

        int i = 100;
        while (i > 0) {
            JSONObject jsonObject = JSON.parseObject(message);
            jsonObject.put("id", i);
            String jsonString = jsonObject.toJSONString();

            rabbitTemplatetopic.convertAndSend(exchange, routingKey, jsonString,
                    m -> {
                        m.getMessageProperties().setContentType("application/json");
                        m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        return m;
                    });
            log.info("-----> 发送完成消息完成，交换机名称为{}，routingKey名称为{}，发送的消息是{}", exchange, routingKey, jsonString);
            i--;
        }
    }
}
