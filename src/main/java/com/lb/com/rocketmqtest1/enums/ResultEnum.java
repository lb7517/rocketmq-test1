package com.lb.com.rocketmqtest1.enums;

/*
 * @Author liub
 * @Description 返回结果枚举类，在此定义自定义异常的code和msg
 * 2，4，5开头对接前端
 * 6，7开头使用业务逻辑异常
 *
 * @Date 2020-11-25 10:40
 */
public enum ResultEnum {

    SUCCESS(0, "请求成功"),
    FAIL(1, "请求失败"),
    SYS_REQUEST_PARAMS_ERROR(40000, "参数错误"),
    NO_LOGIN(40001, "用户未登录，或不存在该账号!"),
    NO_AUTH_API(40003, "您的账号无访问权限"),
    UNKOWN_ERROR(50000, "系统未知异常"),
    OPERATION_DATA(50001,"操作失败"),
    SYSTEM_BUSY(50002,"系统繁忙"),

    // 业务逻辑的使用6和7开头
    FAIL_LOGOUT(60200, "用户登录注销失败!"),
    NO_DATA(60003, "查询无结果！"),
    NO_RESULT(60004, "无可调度服务！"),
    REDIS_ERROR(60005, "redis出错！"),
    REPEAT_DATA(60006, "数据重复"),
    OUTER_ERROR(60007, "调用外部接口失败"),
    REPEAT_OPEN(60008, "请勿重复开启"),
    NO_OPEN(60009, "无开启的截图服务"),
    NO_SERVICE(60010, "无可调度服务"),
    NO_UID(60011, "重新分配异常：uid不存在"),
    OPEN_REPORT(60012, "请求账号设备权限异常"),

    ERROR_ROLE(60013, "接口请求角色不对"),
    ERROR_ROLE_NO_CHILD(60014, "该角色应无子用户参数"),
    ERROR_CUSREGION_NULL(60015, "新增的自定义区域不能为空"),
    ERROR_PCUSREGION_NULL(60016, "该父自定义区域无效"),
    ERROR_CUSREGION_EXIST(60017, "部分自定义区域已存在"),
    ERROR_CUSREGION_EXIST_DEV(60018, "该区域下存在设备不可删除"),
    ERROR_CUSREGION_ROOT_EXSIT(60019, "根节点已存在"),
    ERROR_ENTID_NULL(60020, "企业主id不能为空"),
    ERROR_USER_DEVICE_EXIST(60021, "该用户设备已存在"),
    ERROR_CUSTREE_LEVEL(60022, "业务树层级非法"),
    ERROR_ENTER_NOT_EXIST(60023,"找不到企业主"),
    ERROR_ADD_DEVICE_NULL(60024,"新增设备不存在"),


    ERROR_EHOME_REGION(70000, "看家能力开放-区域列表调用错误"),
    FAIL_EHOME_REGION(70001, "看家能力开放-区域列表调用失败"),
    ERROR_EHOME_DEVICEPAGE(70002, "看家能力开放-设备列表调用错误"),
    FAIL_EHOME_DEVICEPAGE(70003, "看家能力开放-设备列表调用失败"),
    ERROR_EHOME_DEVICE(70004, "看家能力开放-设备信息调用错误"),
    FAIL_EHOME_DEVICE(70005, "看家能力开放-设备信息调用失败"),
    FAIL_EHOME_SYNC_RECORD(70006,"行业版根据账号查询同步记录失败"),

    ERROR_GB_REGION(80000,"获取国标目录错误"),
    NO_GB_REGION(80001,"国标区域列表下无区域"),
    ERROR_GB_DEVICEPAGE(80002,"获取设备列表错误"),
    NO_GB_DEVICE(80003,"区域下无设备"),
    ERROR_GB_DEVICE(80004,"设备信息详情调用错误"),
    FAIL_GB_DEVICE(80005,"设备信息详情调用失败"),
    ERROR_PATH_DEVICE_NUM(80006,"区域和设备数调用错误"),

    REGION_NOT_EXIST(10007, "区域不存在"),
    P_REGION_CODE_NOT_EXIST(10008, "父区域不存在"),
    OUT_OF_REGION_INRC(10009, "6位区域编码递增数溢出"),
    REGION_HAS_DEVECE(10010, "该区域及子区域存在设备，不能删除"),
    NO_PERMS_ACCESS_REGION(10011, "用户无权限访问该区域"),
    DEVICE_BOUND(10012, "该设备已绑定"),
    NO_DEVICE_ALLOCATE(10013, "该区域没有可分配的设备"),
    TIME_COVERT_EXCEPTION(10014, "时间转换异常"),
    ;


    private int code;

    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
