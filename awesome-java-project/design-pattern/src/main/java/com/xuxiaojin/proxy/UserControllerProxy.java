package com.xuxiaojin.proxy;

public class UserControllerProxy implements IUserController {
    private MetricsCollector metricsCollector;
    private UserController userController;

    public UserControllerProxy(UserController userController) {
        this.userController = userController;
        this.metricsCollector = new MetricsCollector();
    }

    @Override
    public String login(String username, String password) {
        String res = userController.login("xujin", "xujin");
        metricsCollector.record("requestInfo");
        return res;
    }

    @Override
    public String register(String telephone, String password) {
        String res = userController.register("111", "xujin");
        metricsCollector.record("requestInfo");
        return res;
    }
}
