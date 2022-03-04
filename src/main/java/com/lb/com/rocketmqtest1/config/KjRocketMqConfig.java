package com.lb.com.rocketmqtest1.config;

import com.lb.com.rocketmqtest1.mq.EhomeDeviceMQConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueConsistentHash;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Package com._21cn.ehomesync.config
 * @Author Liub
 * @Description 看家mq配置文件，供初始化
 * @Date 2022/2/22 17:46
 */
@Configuration
@Slf4j
public class KjRocketMqConfig {

    @Value("${rocketMq.consumer.message.consumeThreadMin:1}")
    private int groupMessageConsumeThreadMin;

    @Value("${rocketMq.consumer.message.consumeThreadMax:1}")
    private int groupMessageConsumeThreadMax;

    @Value("${rocketMq.consumer.message.consumeMessageBatchMaxSize:1}")
    private int groupMessageConsumeMessageBatchMaxSize;

    @Value("${rocketMq.consumer.upDevName.tag:*}")
    private String updateDevNameTag;

    @Value("${kj.rocketmq.nameServerAddr:127.0.0.1:9876}")
    private String kjMqAddress;

    @Value("${rocketMq.consumer.upDevTest.groupName:group_vcp_ehome_device_test_update}")
    private String updateDevGroupName;

    @Value("${rocketMq.consumer.upDevTest.topic:topic_ehome_device_test_update}")
    private String updateDevTestTopic;
    /**
     * 消息最大长度 默认1024*4(4M)
     */
    @Value("${rocketmq.producer.maxMessageSize:4096}")
    private Integer maxMessageSize;

    /**
     * 发送消息超时时间,默认3000
     */
    @Value("${rocketmq.producer.sendMsgTimeout:3000}")
    private Integer sendMsgTimeout;

    /**
     * 发送消息失败重试次数，默认2
     */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed:2}")
    private Integer retryTimesWhenSendFailed;

    @Autowired
    private EhomeDeviceMQConsumer ehomeDeviceMQConsumer;

    @PostConstruct
    public void devUpdateMessageListener() {

        startMQConsumer(kjMqAddress, updateDevGroupName, groupMessageConsumeThreadMin,
                groupMessageConsumeThreadMax, updateDevTestTopic, updateDevNameTag,
                groupMessageConsumeMessageBatchMaxSize, ehomeDeviceMQConsumer);
    }

    public void startMQConsumer(String address, String groupName, int consumeThreadMin, int consumeThreadMax
            , String topic, String tag, int consumeMessageBatchMaxSize, MessageListenerConcurrently messageListenerConcurrently) {
    /*public void startMQConsumer(String address, String groupName, int consumeThreadMin, int consumeThreadMax
            , String topic, String tag, int consumeMessageBatchMaxSize, MessageListenerOrderly messageListenerConcurrently) {*/
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(address);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(messageListenerConcurrently);
        consumer.setMessageModel(MessageModel.CLUSTERING);
//        consumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueConsistentHash());
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置一次消费消息的条数，默认为1条
         */
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            consumer.subscribe(topic, tag);
            //设置instance name ，避免创建的消费者被覆盖,用组名称代替
            consumer.setInstanceName(groupName);
            consumer.start();
            log.info("start deviceUpdate consumer success!groupName: {}", groupName);
        } catch (Exception e) {
            log.error("convert to json obj error", e);
        }
    }

    /**
     * 生产者bean
     */
    @Bean(name = "deviceKjMqProducer")
//    @Bean
    public DefaultMQProducer getRocketMQProducer() {
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.updateDevGroupName);
        producer.setNamesrvAddr(this.kjMqAddress);
        //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
        if (this.maxMessageSize != null) {
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if (this.sendMsgTimeout != null) {
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if (this.retryTimesWhenSendFailed != null) {
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            log.error("{}", e);
        }
        return producer;
    }
}
