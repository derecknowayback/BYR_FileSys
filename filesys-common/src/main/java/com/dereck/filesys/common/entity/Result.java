package com.dereck.filesys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 封装一个实体类，用于和前端交互
 */
@Data
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    /**
     * 正确返回
     * @return 返回一个正确的Result
     */
    public static Result ok(){
        return new Result(true, null, null, null);
    }

    /**
     * @param data 返回时捎带的数据
     * @return
     */
    public static Result ok(Object data){
        return new Result(true, null, data, null);
    }

    /**
     * @param data 返回多元素
     * @param total 总共成功的条数 ？？？
     * @return
     */
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, total);
    }

    /**
     * @param errorMsg 返回错误信息
     * @return
     */
    public static Result fail(String errorMsg){
        return new Result(false, errorMsg, null, null);
    }
}
