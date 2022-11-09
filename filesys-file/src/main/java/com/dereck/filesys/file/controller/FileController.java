package com.dereck.filesys.file.controller;

import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.SFile;
import com.dereck.filesys.file.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * @param filename 请求的文件名
     * @return 返回一个Map，key: “文件名字-上传者” , value: 文件下载的url
     */
    @GetMapping("/download/{filename}")
    public Map<String,Object> downFile(@PathVariable("filename")String filename){
        List<SFile> files = fileService.getDownLoadUrl(filename);
        return files.stream().collect(Collectors.toMap(x->x.getName()+"-"+x.getUpLoader(),x->x.getUrl())); // jdk8 Stream流操作
    }


    /**
     *  根据文件名字，返回文件的有关信息
     * @param  filename 请求的文件名
     * @return 单个文件对象
     */
    @GetMapping("/preview/{filename}")
    public R previewFile(@PathVariable String filename){
        SFile sFile = fileService.previewFile(filename);
        if(sFile == null){
            return R.fail("没有这个文件",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return R.ok(sFile);
    }

    /**
     *  展示所有的文件
     * @return 一个文件集合
     */
    @GetMapping("/listfiles")
    public List<R> listAllFile(){
        List<SFile> allFiles = fileService.list();
        ArrayList<R> res = new ArrayList<>();
        for (SFile file : allFiles) {
            res.add(R.ok(file));
        }
        return res;
    }
}
