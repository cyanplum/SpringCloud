package org.uppower.sevenlion.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.uppower.sevenlion.common.enums.ResultCode;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2020/8/9 10:03 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unchecked")
public class CommonResult<T> {

    private long code;

    private String msg;

    private T data;

    private boolean success;

    public CommonResult(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(T data) {
        this.data = data;
    }

    public static <T> CommonResult<T> success(long code, String msg, T data) {
        return new CommonResult(code, msg, data, true);
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), msg, data, true);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(ResultCode.SUCCESS.getCode(), "成功", data, true);
    }
    public static <T> CommonResult<T> success(){
        return new CommonResult(ResultCode.SUCCESS.getCode(), "成功",null, true);
    }

    public static <T> CommonResult<T> failed(long code, String msg, T data) {
        return new CommonResult(code, msg, data, false);
    }

    public static <T> CommonResult<T> failed(String msg, T data) {
        return new CommonResult(ResultCode.FAILED.getCode(), msg, data, false);
    }

    public static <T> CommonResult<T> failed(long code, String msg) {
        return new CommonResult(code, msg, null, false);
    }

    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult(ResultCode.FAILED.getCode(), msg, null, false);
    }

    public static <T> CommonResult<T> failMsg(String msg) {
        return new CommonResult(ResultCode.FAILED.getCode(), msg, null, false);
    }

    public static <T> CommonResult<T> failMsg(String msg, T data) {
        return new CommonResult(ResultCode.FAILED.getCode(), msg, data, false);
    }

}
