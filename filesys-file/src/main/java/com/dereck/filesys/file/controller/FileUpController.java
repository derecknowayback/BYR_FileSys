package com.dereck.filesys.file.controller;

import com.dereck.filesys.common.entity.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUpController {

    @PostMapping("/file/upload")
    public R uploadFile(){
        // todo 完成上传文件的接口
        return null;
    }



}
