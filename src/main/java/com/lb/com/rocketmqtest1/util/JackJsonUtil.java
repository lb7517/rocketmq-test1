package com.lb.com.rocketmqtest1.util;

/***
 * @Author liub
 * @Description json转化工具类，基于jackjson
 * todo: 需要其他属性在封装
 * @Date 2020-11-25 10:40
 * */
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class JackJsonUtil {

    public static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将对象序列号json字符串
     *
     * @param obj 待转数据类型
     * @return String json字符串
     */
    public static String beanToJson(Object obj) {

        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
           log.error("jackJson beanToJson error", e);
        }
        return null;
    }


    /**
     * 将json字符串反序列号成对象
     *
     * @param <T>   目标数据类型
     * @param json  待转json字符串
     * @param clazz 目标数据clazz
     * @return
     */
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("jackJson jsonToBean error", e);
        }
        return null;
    }

}
