package com.fans.repository;

import com.fans.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName UserInfoRepository
 * @Description:
 * @Author fan
 * @Date 2019-01-15 15:54
 * @Version 1.0
 **/
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUsernameAndPassword(String username, String password);
}
