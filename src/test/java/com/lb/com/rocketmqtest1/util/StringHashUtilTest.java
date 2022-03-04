package com.lb.com.rocketmqtest1.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Package com.lb.com.rocketmqtest1.util
 * @Author Liub
 * @Description 字符串转hash测试
 * @Date 2022/3/3 9:55
 */
@Slf4j
public class StringHashUtilTest {
    public static void main(String[] args) {
//        String result = StringHashUtil.toHash("23gsdf");
        int result = StringHashUtil.toHash("23gsdf");
        log.info("result: {}", result);
    }
}
