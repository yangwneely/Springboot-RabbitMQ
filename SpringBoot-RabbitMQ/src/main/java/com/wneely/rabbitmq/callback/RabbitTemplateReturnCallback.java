//package com.wneely.rabbitmq.callback;
//
//import com.alibaba.fastjson.JSON;
//import com.wneely.dao.TbMessageDao;
//import com.wneely.entity.TbMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.UUID;
//
///**
// * ‌确认机制‌：通过 setConfirmCallback 回调确认消息是否被交换机接收。
// * ‌返回机制‌：通过 setReturnCallback 处理因路由错误、队列不存在等导致的投递失败。
// * 消息发送到交换器成功，但是没有匹配的队列就会触发回调
// */
//@Slf4j
//@Component
//public class RabbitTemplateReturnCallback implements RabbitTemplate.ReturnCallback {
//    @Autowired
//    private TbMessageDao tbMessageDao;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * PostConstruct该方法会在构造函数执行完毕后、对象正式使用前调用，主要用于初始化操作
//     */
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setReturnCallback(this);
//        //当设置为 true 时，表示消息必须被送达目标队列，否则会触发回调函数处理失败情况。这是实现消息可靠性投递的关键配置之一
//        // 通常与 setPublisherConfirms(true)、setPublisherReturns(true) 配合使用，构成消息确认与返回机制
//        rabbitTemplate.setMandatory(true);
//    }
//
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        TbMessage mes = new TbMessage();
//        mes.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//        mes.setMessage(JSON.toJSONString(message));
//        mes.setReplyCode(replyCode);
//        mes.setReplyText(replyText);
//        mes.setExchange(exchange);
//        mes.setRoutingKey(routingKey);
//        tbMessageDao.insert(mes);
//
//        log.info("主体消息：{}", message);
//        log.info("响应码：{}", replyCode);
//        log.info("响应报文：{}", replyText);
//        log.info("消息使用的交换器：{}", exchange);
//        log.info("消息使用的路由键：{}", routingKey);
//
//    }
//}
