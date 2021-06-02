package com.xuxiaojin.proxy;

public class UserController implements IUserController {
    @Override
    public String login(String username, String password) {
        return username + "login!";
    }

    @Override
    public String register(String telephone, String password) {
        return "welcome";
    }
}
