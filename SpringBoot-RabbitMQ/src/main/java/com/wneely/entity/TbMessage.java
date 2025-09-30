package com.wneely.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class TbMessage {
    private String id;
    private String correlationData;
    private String ack;
    private String cause;
    private String message;
    private int replyCode;
    private String replyText;
    private String exchange;
    private String routingKey;
}
