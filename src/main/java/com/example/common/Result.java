package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回接口结果
 *
 * @author Administrator
 * @since 2026-11-06 上午 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    //成功不需要给前端返回数据
    public static Result success() {
        Result result = new Result();
        result.setCode("200");
        result.setMsg("请求成功");
        return result;
    }
    //成功需要给前端返回数据
    public static Result success(Object data) {
        Result result = success();
        result.setData(data);
        return result;

    }

    //失败不需要给前端返回数据
    public static Result error() {
        Result result = new Result();
        result.setCode("500");
        result.setMsg("请求失败");
        return result;
    }

    //失败需要给前端返回错误信息
    public static Result error(String code, String msg) {
        Result result = error();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
