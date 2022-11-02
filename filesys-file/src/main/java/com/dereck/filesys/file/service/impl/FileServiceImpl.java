package com.dereck.filesys.file.service.impl;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dereck.filesys.common.constant.MinioConstant;
import com.dereck.filesys.common.entity.SFile;
import com.dereck.filesys.common.entity.User;
import com.dereck.filesys.file.dto.FileMapper;
import com.dereck.filesys.file.service.FileService;
import com.dereck.filesys.file.utils.MinioUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, SFile> implements FileService {

    @Resource
    FileMapper mapper;

    User user;


    public SFile uploadFile(MultipartFile file, Integer expireTime, TimeUnit timeUnit) throws Exception {
        // 生成唯一文件名字，策略：id + 文件名
        String userName = "ApiFox";
        SFile newFile = MinioUtils.upLoadObject(MinioConstant.BUCKET_NAME, userName, file, expireTime, timeUnit);
        // 将url存到数据库里
        save(newFile);
        return newFile;
    }


    /**
     * 从数据库中查询url
     * @param fileName 文件的关键词
     * @return 返回值 所有含有 "filename" 的文件
     */
    public List<SFile> getDownLoadUrl(String fileName) {
        LambdaQueryChainWrapper<SFile> queryChainWrapper = lambdaQuery()
                .select(SFile::getUrl, SFile::getUpLoader, SFile::getName, SFile::getUpTime)
                .like(SFile::getName, "%" + fileName + "%").orderByDesc(SFile::getUpTime);
        return list(queryChainWrapper);
    }

}
