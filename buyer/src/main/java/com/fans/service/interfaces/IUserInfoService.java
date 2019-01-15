package com.fans.service.interfaces;

/**
 * @InterfaceName IUserInfoService
 * @Description:
 * @Author fan
 * @Date 2019-01-15 16:02
 * @Version 1.0
 **/
public interface IUserInfoService {
    boolean findByUsernameAndPassword(String username, String password);
}
