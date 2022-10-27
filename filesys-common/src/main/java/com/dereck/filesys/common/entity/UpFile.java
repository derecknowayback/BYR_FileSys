package com.dereck.filesys.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UpFile {
    /**主键*/
    private Long id;

    /**文件名字*/
    private String name;

    /**文件url*/
    private String url;

    /**文件上传者*/
    private User upLoader;

    /**文件上传时间*/
    private LocalDateTime upTime;



}
