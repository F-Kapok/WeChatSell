package com.fans.service.impl;

import com.fans.pojo.UserInfo;
import com.fans.repository.UserInfoRepository;
import com.fans.service.interfaces.IUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName UserInfoServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-15 16:04
 * @Version 1.0
 **/
@Service(value = "iUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Resource(type = UserInfoRepository.class)
    private UserInfoRepository userInfoRepository;

    @Override
    public boolean findByUsernameAndPassword(String username, String password) {
        UserInfo userInfo = userInfoRepository.findByUsernameAndPassword(username, password);
        if (userInfo == null) {
            return false;
        }
        return true;
    }
}
