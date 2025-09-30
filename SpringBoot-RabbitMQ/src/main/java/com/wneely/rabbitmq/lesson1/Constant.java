package com.wneely.rabbitmq.lesson1;

public class Constant {
    public final static String DOT2DOT_QUEUENAME = "dot2dot-queue";
    public final static String DOT2MORE_QUEUENAME = "dot2more-queue";
    public final static String FANOUT1_QUEUENAME = "fanout1-queue";
    public final static String FANOUT2_QUEUENAME = "fanout2-queue";
    public final static String DIRECT1_QUEUENAME = "direct1-queue";
    public final static String DIRECT2_QUEUENAME = "direct2-queue";
    public final static String TOPIC1_QUEUENAME = "topic1-queue";
    public final static String TOPIC2_QUEUENAME = "topic2-queue";

    public final static String FANOUT_EXCHANGE = "fanout-exchange";
    public final static String DIRECT_EXCHANGE = "direct-exchange";
    public final static String TOPIC_EXCHANGE = "topic-exchange";

    public final static String DIRECT1_ROUTINGKEY = "direct1-key";
    public final static String DIRECT2_ROUTINGKEY = "direct2-key";

    //<speed>.<colour>.<species>
    public final static String TOPIC1_ROUTINGKEY = "quick.orange.car";
    public final static String TOPIC2_ROUTINGKEY = "slow.white.rabbit";
    public final static String TOPIC3_ROUTINGKEY = "lazy.black.person";


}
