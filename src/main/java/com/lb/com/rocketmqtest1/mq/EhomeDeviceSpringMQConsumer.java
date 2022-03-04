/*
package com.lb.com.rocketmqtest1.mq;

import com.lb.com.rocketmqtest1.common.Constants;
import com.lb.com.rocketmqtest1.entity.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

*/
/**
 * @Package com._21cn.ehomesync.mq
 * @Author Liub
 * @Description 设备变信息消费测试
 * @Date 2022/3/2 17:49
 *//*

@Slf4j
@Service
@RocketMQMessageListener(topic = Constants.DEVICE_INFO_TOPIC,
        consumerGroup = Constants.DEVICE_INFO_GROUP_NAME,
        consumeMode = ConsumeMode.ORDERLY)
public class EhomeDeviceSpringMQConsumer implements RocketMQListener<DeviceInfo> {
    @Override
    public void onMessage(DeviceInfo deviceInfo) {
        log.info("客户端手动触发生产区域消息 DeviceInfo: {}", deviceInfo);
    }
}
*/
