//package com.wneely.rabbitmq.callback;
//
//import com.wneely.dao.TbContextDao;
//import com.wneely.dao.TbMessageDao;
//import com.wneely.entity.TbMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.UUID;
//
//import com.alibaba.fastjson.JSON;
//
///**
// * 配置ACK确认回调的配置，通过实现RabbitTemplate.ConfirmCallback接口，
// * 消息发送到Broker后触发回调，也就是只能确认是否正确到达Exchange中。
// */
//@Slf4j
//@Component
//public class RabbitTemplateConfirmCallback implements RabbitTemplate.ConfirmCallback {
//    @Autowired
//    private TbContextDao tbContextDao;
//    @Autowired
//    private TbMessageDao tbMessageDao;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
//    }
//
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        log.info("消息唯一标识：{},确认结果：{},失败原因：{}", correlationData, ack, cause);
//        TbMessage message = new TbMessage();
//        message.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        message.setCorrelationData(JSON.toJSONString(correlationData));
//        message.setAck(ack + "");
//        message.setCause(cause);
//
//        tbMessageDao.insert(message);
//    }
//}
