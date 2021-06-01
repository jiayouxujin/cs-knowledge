package com.xuxiaojin.singleton.logger.wrongdemo;

import com.xuxiaojin.singleton.logger.Logger;

public class UserController {
    private Logger logger = new Logger();

    public void login(String username, String password) {
        //业务逻辑代码
        logger.log(username + "login");
    }
}
