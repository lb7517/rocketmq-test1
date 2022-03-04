package com.lb.com.rocketmqtest1.entity;

import com.lb.com.rocketmqtest1.enums.ResultEnum;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * @Author liub
 * @Description 自定义统一返回结构
 * @Date 2020-11-25 10:40
 */
@ToString
@NoArgsConstructor
public class ResultDTO<T> {

    private int code;

    private String msg;

    private T data;

    public ResultDTO<T> setSuccessAndData(T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
        return this;
    }

    public ResultDTO(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public ResultDTO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDTO(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDTO setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultDTO setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultDTO setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ResultDTO<T> success(){
        return new ResultDTO<T>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
    }

    public static <T> ResultDTO<T> success(String msg){
        return new ResultDTO<T>(ResultEnum.SUCCESS.getCode(), msg);
    }

    public static <T> ResultDTO<T> success(String msg, T data){
        return new ResultDTO<T>(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static <T> ResultDTO<T> success(T data){
        return new ResultDTO<T>().setSuccessAndData(data);
    }

    public static <T> ResultDTO<T> fail(){
        return new ResultDTO<T>(ResultEnum.FAIL.getCode(), ResultEnum.FAIL.getMsg());
    }

    public static <T> ResultDTO<T> fail(String msg){
        return new ResultDTO<T>(ResultEnum.FAIL.getCode(), msg);
    }

    public static <T> ResultDTO<T> fail(String msg, T data){
        return new ResultDTO<T>(ResultEnum.FAIL.getCode(), msg, data);
    }

    public static <T> ResultDTO<T> resp(int code, String msg, T data){
        return new ResultDTO<T>(code, msg, data);
    }

    public static ResultDTO toResult(int rows){
        return rows > 0 ? success() : fail();
    }


}
