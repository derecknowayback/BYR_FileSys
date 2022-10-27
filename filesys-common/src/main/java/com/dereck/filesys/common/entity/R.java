package com.dereck.filesys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * 封装一个实体类，用于和前端交互
 */
@Data
@AllArgsConstructor
public class R {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;
    private HttpStatus httpStatus;
    /**
     * 正确返回
     * @return 返回一个正确的Result
     */
    public static R ok(){
        return new R(true, null, null, null,HttpStatus.ACCEPTED);
    }

    /**
     * @param data 返回时捎带的数据
     * @return
     */
    public static R ok(Object data){
        return new R(true, null, data, null,HttpStatus.ACCEPTED);
    }

    /**
     * @param data 返回多元素
     * @param total 总共成功的条数 ？？？
     * @return
     */
    public static R ok(List<?> data, Long total){
        return new R(true, null, data, total,HttpStatus.ACCEPTED);
    }

    /**
     * @param errorMsg 返回错误信息
     * @return
     */
    public static R fail(String errorMsg,HttpStatus httpStatus){
        return new R(false, errorMsg, null, null,httpStatus);
    }
}