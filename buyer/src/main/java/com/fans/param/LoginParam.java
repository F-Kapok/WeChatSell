package com.fans.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ClassName LoginParam
 * @Description:
 * @Author fan
 * @Date 2019-01-15 15:59
 * @Version 1.0
 **/
@Data
public class LoginParam {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
