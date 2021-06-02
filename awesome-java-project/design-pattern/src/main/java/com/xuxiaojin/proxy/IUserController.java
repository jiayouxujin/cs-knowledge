package com.xuxiaojin.proxy;

public interface IUserController {
    String login(String username, String password);

    String register(String telephone, String password);
}
