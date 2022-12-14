package com.dereck.filesys.file;

import com.dereck.filesys.common.entity.SFile;
import com.dereck.filesys.file.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Resource
    FileService fileService;

    @Test
    public void testInsertFile(){
        fileService.save(new SFile("aaa","bbb","ccc", LocalDateTime.now(),123L));
    }

    @Test
    public void testGetUrl(){
        fileService.getDownLoadUrl("file").forEach(System.out::println);
    }

    @Test
    public void testLambda(){
        fileService.lambdaQuery()
                .select(SFile::getUrl, SFile::getUpLoader, SFile::getName,SFile::getUpTime)
               .orderByDesc(SFile::getUpTime).list().forEach(System.out::println);
    }


}
