package com.lb.com.rocketmqtest1.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @Package com._21cn.ehomesync.utils
 * @Author Liub
 * @Description 字符串转成hash
 * @Date 2022/3/2 18:10
 */
@Slf4j
public class StringHashUtil {
    /**
     * 将字符串转成hash值
     * @param key 需要转换的字符串
     * */
    public static int toHash(String key) {
        // 数组大小一般取质数
        int arraySize = 11113;
        int hashCode = 0;
        // 从字符串的左边开始计算
        for (int i = 0; i < key.length(); i++) {
            // 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1,就代表a的值，同理b=2；
            int letterValue = key.charAt(i);
            // 防止编码溢出，对每步结果都进行取模运算
            hashCode = ((hashCode << 5) + letterValue) % arraySize;
        }
        return hashCode;
    }
}
