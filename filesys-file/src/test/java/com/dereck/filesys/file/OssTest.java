package com.dereck.filesys.file;

import com.aliyun.oss.OSS;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OssTest {

    @Resource
    OSS ossClient;

    @Test
    public void testUpdate() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\pic\\KaMakura.jpg");
        ossClient.putObject("byr-file-sys","KaMakura.jpg",fileInputStream);
        ossClient.shutdown();
        System.out.println("okk");
    }

}
