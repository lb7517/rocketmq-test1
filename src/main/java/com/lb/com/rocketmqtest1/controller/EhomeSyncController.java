package com.lb.com.rocketmqtest1.controller;

import com.lb.com.rocketmqtest1.entity.DeviceInfo;
import com.lb.com.rocketmqtest1.entity.ResultDTO;
import com.lb.com.rocketmqtest1.enums.ResultEnum;
import com.lb.com.rocketmqtest1.mq.EhomeDeviceTestMQProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com._21cn.ehomesync.mq
 * @Author Liub
 * @Description 设备变信息消费测试
 * @Date 2022/3/2 17:49
 */
@Slf4j
@RestController
public class EhomeSyncController {

    @Autowired
    private EhomeDeviceTestMQProduct ehomeDeviceTestMQProduct;

    /**
     * 使用rocketmq-client方式使用功能rocketmq生产消息，并且指定分区
     * */
    @RequestMapping("/device1")
    public ResultDTO<Void> deviceInfo1(@RequestBody DeviceInfo deviceInfo) {
        log.info("EhomeSyncController  deviceInfo：{}", deviceInfo);
        try {
            ehomeDeviceTestMQProduct.deviceMqProduct1(deviceInfo);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResultDTO<>(ResultEnum.SUCCESS);
    }

    /**
     * 使用rocketmq-spring-boot-starter springboot集成方式使用功能rocketmq生产消息，并且指定分区
     * */
    /*@RequestMapping("/device2")
    public ResultDTO<Void> deviceInfo2(@RequestBody DeviceInfo deviceInfo) {
        log.info("EhomeSyncController  deviceInfo：{}", deviceInfo);
        try {
            ehomeDeviceTestMQProduct.deviceMqProduct2(deviceInfo);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return new ResultDTO<>(ResultEnum.SUCCESS);
    }*/
}
