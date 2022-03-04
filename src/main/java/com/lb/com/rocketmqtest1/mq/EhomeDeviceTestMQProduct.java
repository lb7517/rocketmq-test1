package com.lb.com.rocketmqtest1.mq;

import com.lb.com.rocketmqtest1.entity.DeviceInfo;
import com.lb.com.rocketmqtest1.util.JackJsonUtil;
import com.lb.com.rocketmqtest1.util.StringHashUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Package com._21cn.ehomesync.mq
 * @Author Liub
 * @Description 设备变更生产测试
 * @Date 2022/3/2 17:50
 */
@Slf4j
@Component
public class EhomeDeviceTestMQProduct {

    @Value("${rocketMq.consumer.upDevTest.topic:topic_ehome_device_test_update}")
    private String updateDevTestTopic;

    @Value("${rocketMq.consumer.upDevName.tag:*}")
    private String updateDevNameTag;

    @Resource
    @Qualifier("deviceKjMqProducer")
    private DefaultMQProducer defaultMQProducer;

   /* @Autowired
    private RocketMQTemplate rocketMQTemplate;*/

    /**
     * rocketmq生产方案二:  设备信息生产者
     * */
    public void deviceMqProduct1(DeviceInfo deviceInfo) throws Exception {
        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message(updateDevTestTopic,
                updateDevNameTag,
                JackJsonUtil.beanToJson(deviceInfo).getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        //Call send message to deliver message to one of brokers.
        // 指定分区发送消息
        SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
            // mqs.size()表示分区数量
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                int index = Math.abs(String.valueOf(arg).hashCode()) % mqs.size();
                log.info("生产消息入分区 queue: {}", index);
                return mqs.get(index);
            }
        }, deviceInfo.getDeviceCode());
        log.info("start sendResult: {}", sendResult);
    }

    /**
     * rocketmq生产方案二: 使用springboot-rocket集成包实现(rocketmq-spring-boot-starter)， 设备信息生产者
     * */
    /*public void deviceMqProduct2(DeviceInfo deviceInfo) {
        log.info("deviceMqProduct2 msg:{}", JackJsonUtil.beanToJson(deviceInfo));
//        rocketMQTemplate.convertAndSend(updateDevTestTopic, deviceInfo);
        // 通过设备码字符传hash码指定分区
        SendResult result = rocketMQTemplate.syncSendOrderly(updateDevTestTopic, deviceInfo,
                deviceInfo.getDeviceCode(), 3000);
        log.info("SendResult: {}", result);
    }*/
}
