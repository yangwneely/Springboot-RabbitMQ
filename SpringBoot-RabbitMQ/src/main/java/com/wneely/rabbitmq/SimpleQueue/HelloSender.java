//package com.wneely.rabbitmq.SimpleQueue;
//
//import com.wneely.entity.TbContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Slf4j
//@Component
//public class HelloSender {
//
//    public static final String MESSAGES1 = "这是我发送的消息1：";
//    public static final String MESSAGES2 = "这是我发送的消息1：";
//
//    private static final String ROUTINGKEY1 = "hellow";
//    private static final String ROUTINGKEY2 = "simple";
//    @Autowired
//    @Qualifier("rabbitTemplate")
//    private RabbitTemplate rabbitTemplate;
//
//    public void send() {
//        TbContext context = new TbContext();
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        context.setId(uuid);
//        context.setContext(MESSAGES1 + uuid);
//        //发送消息到hello队列
//        log.info("发送消息：{}", context);
//        rabbitTemplate.convertAndSend(ROUTINGKEY1, context, new CorrelationData(uuid));
//
//        log.info("发送消息：{}", MESSAGES1);
//        rabbitTemplate.convertAndSend(ROUTINGKEY2, MESSAGES1);
//        System.out.println("<--------发送完成-------->");
//    }
//}
