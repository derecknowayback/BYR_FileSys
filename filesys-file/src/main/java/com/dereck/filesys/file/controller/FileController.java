package com.dereck.filesys.file.controller;

import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.SFile;
import com.dereck.filesys.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;


    /**
     *  上载文件的接口
     * @param file  等待上传的文件
     * @return 上传成功就返回
     */
    @PostMapping("/upload")
    public R uploadFile(@RequestBody MultipartFile file, @RequestParam(value = "expire",defaultValue = "-1")Integer expireTime){
        SFile sFile;
        try {
             sFile = fileService.uploadFile(file,expireTime,TimeUnit.DAYS);
        }catch (Exception e){
            return R.fail(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return R.ok(sFile);
    }


    /**
     * 返回文件下载的url，支持模糊查询
     * @param
     * @return 返回一个Map，key: “文件名字-上传者” , value: 文件下载的url
     */
    @GetMapping("/download/{filename}")
    public Map<String,Object> downFile(@PathVariable("filename")String filename){
        List<SFile> files = fileService.getDownLoadUrl(filename);
        return files.stream().collect(Collectors.toMap(x->x.getName()+"-"+x.getUpLoader(),x->x.getUrl())); // jdk8 Stream流操作
    }



    @GetMapping("/preview/{filename}")
    public R previewFile(@PathVariable String filename){
        // todo  预览文件
        return null;
    }






}
