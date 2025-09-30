package com.wneely.rabbitmq.lesson1.exchange.topic;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.wneely.rabbitmq.lesson1.Constant.TOPIC2_QUEUENAME;

@Slf4j
@Component("topicWorker3Receiver")
public class TopicWorker3Receiver {

    @RabbitListener(queues = TOPIC2_QUEUENAME)
    public void receiverMessage(Object message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        log.info("接受到消息，消息队列名称为{}，消息为{}，deliveryTag为{}", TOPIC2_QUEUENAME, message, deliveryTag);
        //第一个参数表示消息的唯一标识，
        // 第二个参数控制确认范围：
        // true：批量确认模式，会一次性确认所有小于或等于当前deliveryTag的未确认消息。例如，若通道中存在未确认消息的tag为5、6、7、8，确认tag=8且multiple=true时，将同时确认tag=5至8的所有消息
        //false‌：仅确认当前deliveryTag对应的单条消息，更精确但效率较低
        channel.basicAck(deliveryTag,false);
        channel.basicQos(1);//prefetchCount=1 Worker处理并确认上一条消息之前，不要向它发送新消息
    }
}
