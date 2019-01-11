package com.fans.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JsonData
 * @Description: 自定义交互数据定义类
 * @Author fan
 * @Date 2018-11-20 09:44
 * @Version 1.0
 **/
@NoArgsConstructor
@Getter
@Setter
public class JsonData<T> implements Serializable {

    private static final long serialVersionUID = 123456789L;

    private Integer code;

    private String msg = "";

    private T data;

    private JsonData(Integer code) {
        this.code = code;
    }

    private JsonData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private JsonData(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private JsonData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> JsonData<T> success(String msg, T data) {
        return new JsonData<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> JsonData<T> success(T data) {
        return new JsonData<>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> JsonData<T> success(String msg) {
        return new JsonData<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> JsonData<T> success() {
        return new JsonData<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> JsonData<T> fail(String msg) {
        return new JsonData<>(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> JsonData<T> failCodeMsg(Integer code, String msg) {
        return new JsonData<>(code, msg);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code.equals(ResponseCode.SUCCESS.getCode());
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = Maps.newHashMap();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
