package com.dereck.filesys.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dereck.filesys.common.entity.SFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface FileService extends IService<SFile>{
    SFile uploadFile(MultipartFile file,  Integer expireTime, TimeUnit timeUnit) throws Exception;

    List<SFile> getDownLoadUrl(String fileName);

    InputStream previewFile(String filename,String username) throws Exception;

}
