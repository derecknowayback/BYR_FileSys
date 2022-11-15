package com.dereck.filesys.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@TableName("files")
public class SFile implements Serializable {

    public static final String UPLOAD_FAILED = "上传失败，请重新上传！";

    /**主键*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /**文件名字*/
    @TableField("file_name")
    private String name;

    /**文件url*/
    @TableField("file_url")
    private String url;

    /**文件上传者*/
    @TableField("file_uploader")
    private String upLoader;

    /**文件上传时间*/
    @TableField("file_date")
    private LocalDateTime upTime;

    public SFile(String name, String url, String upLoader, LocalDateTime upTime) {
        this.name = name;
        this.url = url;
        this.upLoader = upLoader;
        this.upTime = upTime;
    }
}
