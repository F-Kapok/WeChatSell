<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div class="container" style="margin-top: 150px">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="post" action="/sell/seller/login">
                <h2>请登陆</h2>
                <div class="form-group">
                    <label>用户名</label><input type="text" class="form-control" placeholder="username" name="username"/>
                </div>
                <div class="form-group">
                    <label>密码</label><input type="password" class="form-control" name="password"
                                            placeholder="password"/>
                </div>
                <button type="submit" class="btn btn-default btn-primary">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>