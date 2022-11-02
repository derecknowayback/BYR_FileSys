package com.dereck.filesys.file.utils;


import com.dereck.filesys.common.entity.SFile;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class MinioUtils {

    @Resource
    MinioClient minioClient;

    public static MinioUtils unique = new MinioUtils();

    @PostConstruct
    public void init(){
        unique.minioClient = minioClient;
    }

    /**
     *  判断bucket是否存在
     * @param bucketName 桐的名字
     * @return 桶存在就返回true，否则返回false
     * @throws Exception
     */
    public static boolean bucketExists(String bucketName) throws Exception {
        return unique.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     *  如果桶不存在，那么就创建桶
     * @param bucketName 桶的名字
     * @throws Exception
     */
    public static void makeBucketIfNotExist(String bucketName) throws Exception {
        if(!bucketExists(bucketName))
            unique.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
    }


    /**
     *  上传文件到 minio 指定"桶"下指定"用户文件夹"下；
     * @param bucketName 桶的名字
     * @param upName 上传者的名字
     * @param file 文件对象
     * @param expireTime  链接过期时间
     * @param timeUnit  链接过期时间单位
     * @return 返回文件的分享url
     * @throws Exception
     */
    public static SFile upLoadObject(String bucketName, String upName, MultipartFile file, int expireTime, TimeUnit timeUnit) throws Exception{
        unique.minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(upName + "/" + upName +"-" +file.getName())
                .stream(file.getInputStream(),file.getSize(),-1)
                .contentType(file.getContentType())
                .build());

        if(expireTime == -1) {
            expireTime = 10;
            timeUnit = TimeUnit.DAYS;
        }

        String url = unique.minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(upName + "/" + upName +"-" +file.getName())
                        .build());
        return new SFile(file.getName(),url,upName,LocalDateTime.now());
    }

    /**
     *  返回文件
     * @param bucketName
     * @param upName
     * @param fileName
     * @return
     * @throws Exception
     */
    public static InputStream getObject(String bucketName, String upName, String  fileName) throws Exception {
        GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(bucketName).object(upName + "/" + upName + "-"+ fileName).build();
        return unique.minioClient.getObject(objectArgs);
    }

}
