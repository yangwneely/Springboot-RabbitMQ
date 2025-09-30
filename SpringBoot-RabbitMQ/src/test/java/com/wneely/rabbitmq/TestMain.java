package com.wneely.rabbitmq;

import com.wneely.rabbitmq.lesson1.dot2More.Dot2moreSender;
import com.wneely.rabbitmq.lesson1.dot2dot.Dot2dotSender;
import com.wneely.rabbitmq.lesson1.exchange.direct.DirectSender;
import com.wneely.rabbitmq.lesson1.exchange.fanout.FanoutSender;
import com.wneely.rabbitmq.lesson1.exchange.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.wneely.rabbitmq.lesson1.Constant.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
//@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-rabbitmq.xml"})
public class TestMain {

    //    @Autowired
//    private HelloDot2dotSender helloDot2dotSender;
//
//
//    @Test
//    public void sendTest() {
//        helloDot2dotSender.send();
//    }
    private final static String MESSAGE1 = "{\"message\":\"我是发送的点对点消息1\"}";
    private final static String MESSAGE2 = "{\"message\":\"我是发送的点对多消息1\"}";
    private final static String MESSAGE3 = "{\"message\":\"我是发送的fanout消息1\"}";
    private final static String MESSAGE4 = "{\"message\":\"我是发送的direct消息1\"}";
    private final static String MESSAGE5 = "{\"message\":\"我是发送的topic消息1\"}";


    @Autowired
    private Dot2dotSender dot2dotSender;

    @Autowired
    private Dot2moreSender dot2moreSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private TopicSender topicSender;

    @Test
    public void sendDot2dotSender() {
        dot2dotSender.sendMessage(DOT2DOT_QUEUENAME, MESSAGE1);
    }

    @Test
    public void sendDot2moreSender() {
        dot2moreSender.sendMessage(DOT2MORE_QUEUENAME, MESSAGE2);
    }

    @Test
    public void sendFanoutSender() {
        fanoutSender.sendMessage(FANOUT_EXCHANGE, "", MESSAGE3);
        fanoutSender.sendMessage(FANOUT_EXCHANGE, "", MESSAGE3);
    }

    @Test
    public void sendDirectSender() {
        directSender.sendMessage(DIRECT_EXCHANGE, DIRECT1_ROUTINGKEY, MESSAGE4);
        directSender.sendMessage(DIRECT_EXCHANGE, DIRECT2_ROUTINGKEY, MESSAGE4);
    }

    @Test
    public void sendTopicSender() {
        topicSender.sendMessage(TOPIC_EXCHANGE, TOPIC1_ROUTINGKEY, MESSAGE5);
        topicSender.sendMessage(TOPIC_EXCHANGE, TOPIC2_ROUTINGKEY, MESSAGE5);
        topicSender.sendMessage(TOPIC_EXCHANGE, TOPIC3_ROUTINGKEY, MESSAGE5);
    }

}
