package com.wneely.rabbitmq.lesson1.dot2dot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 点到点队列 routingKey要与QueueName相同，没有设置channel、exchange，channel会自己自己创建，默认为""，直连交换机
 */
@Slf4j
@Component("dot2dotSender")
public class Dot2dotSender {

    @Autowired
    @Qualifier(value = "rabbitTemplateDot2dot")
    private RabbitTemplate rabbitTemplateDot2dot;
//    private AmqpTemplate rabbitTemplateDot2dot;

    public void sendMessage(String queueName, String message) {

        int i = 100;
        while (i > 0) {
            JSONObject jsonObject = JSON.parseObject(message);
            jsonObject.put("id", i);
            String jsonString = jsonObject.toJSONString();

            rabbitTemplateDot2dot.convertAndSend(queueName, jsonString,
                    m -> {
                        m.getMessageProperties().setContentType("application/json");
                        return m;
                    });
            log.info("-----> 发送完成消息完成，消息队列名称为{}，发送的消息是{}", queueName, jsonString);
            i--;
        }
    }
}
