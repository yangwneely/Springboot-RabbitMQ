package com.wneely.rabbitmq.lesson1.exchange.fanout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.wneely.rabbitmq.lesson1.Constant.FANOUT1_QUEUENAME;

/**
 * fanout 扇出交换机  广播形式 会忽略routingKey
 */
@Slf4j
@Component("fanoutSender")
public class FanoutSender {

    @Autowired
    @Qualifier(value = "rabbitTemplatefanout")
    private RabbitTemplate rabbitTemplatefanout;
//    private AmqpTemplate rabbitTemplateDot2dmore;

    public void sendMessage(String exchange, String routingKey, String message) {

        int i = 100;
        while (i > 0) {
            JSONObject jsonObject = JSON.parseObject(message);
            jsonObject.put("id", i);
            String jsonString = jsonObject.toJSONString();

            rabbitTemplatefanout.convertAndSend(exchange, FANOUT1_QUEUENAME, jsonString,
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
