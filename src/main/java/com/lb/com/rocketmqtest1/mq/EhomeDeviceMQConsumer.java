package com.lb.com.rocketmqtest1.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Package com._21cn.ehomesync.mq
 * @Author Liub
 * @Description 设备变信息消费测试
 * @Date 2022/3/2 17:49
 */
@Slf4j
@Component
public class EhomeDeviceMQConsumer implements MessageListenerConcurrently {
//public class EhomeDeviceMQConsumer implements MessageListenerOrderly {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msg : list) {
            String resultStr = new String(msg.getBody());
            log.info("queue: {}, device device: {}", msg.getQueueId(), resultStr);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /*@Override
    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        for (MessageExt msg : list) {
            String resultStr = new String(msg.getBody());
            log.info("queue: {}, device device: {}", msg.getQueueId(), resultStr);
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }*/
}
