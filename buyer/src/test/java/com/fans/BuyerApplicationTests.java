package com.fans;

import com.fans.uitls.FtpUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BuyerApplicationTests {

    @Test
    public void contextLoads() {
        List<File> fileList = Lists.newArrayList();
        String pathname = "C:\\Users\\Administrator\\Desktop\\page.jpg";
        File file = new File(pathname);
        fileList.add(file);
        try {
            String uploadFile = FtpUtil.uploadFile(fileList);
            log.info("--> 上传成功，浏览路径为：{}", uploadFile);
        } catch (IOException e) {
            log.error("-->".concat(e.getMessage()), e);
        }
    }

}

