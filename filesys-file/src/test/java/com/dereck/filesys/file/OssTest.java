package com.dereck.filesys.file;


import com.dereck.filesys.common.constant.MinioConstant;
import com.dereck.filesys.file.utils.MinioUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OssTest {



//    @Test
//    public void testUpLoad() throws Exception{
//        MinioUtils.testUpLoad(MinioConstant.BUCKET_NAME,"测试者","D:\\pic\\言叶之庭.jpeg");
//    }
//
//    @Test
//    public void testCreateDir() throws Exception{
//        MinioUtils.testCreateDir(MinioConstant.BUCKET_NAME,"测试者","tester/");
//    }

    @Test
    public void testGetObject() throws Exception{

    }


}
