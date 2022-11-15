package com.dereck.filesys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * 封装一个实体类，用于和前端交互
 */
@Data
@AllArgsConstructor
@ToString
public class R {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private HttpStatus httpStatus;
    /**
     * 正确返回
     * @return 返回一个正确的Result
     */
    public static R ok(){
        return new R(true, null, null, HttpStatus.ACCEPTED);
    }

    /**
     * @param data 返回时捎带的数据
     * @return
     */
    public static R ok(Object data){
        return new R(true, null, data, HttpStatus.ACCEPTED);
    }



    /**
     * @param errorMsg 返回错误信息
     * @return
     */
    public static R fail(String errorMsg,HttpStatus httpStatus){
        return new R(false, errorMsg, null, httpStatus);
    }



}
