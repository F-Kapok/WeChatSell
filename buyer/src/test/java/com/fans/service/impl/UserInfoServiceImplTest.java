package com.fans.service.impl;

import com.fans.service.interfaces.IUserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {
    @Resource(name = "iUserInfoService")
    private IUserInfoService userInfoService;

    @Test
    public void findByUsernameAndPassword() {
        boolean byUsernameAndPassword = userInfoService.findByUsernameAndPassword("kapok", "kapok");
        assertTrue(byUsernameAndPassword);
    }
}