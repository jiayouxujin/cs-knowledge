package com.xuxiaojin.proxy;

public class UserControllerProxy2 extends UserController {
    private MetricsCollector metricsCollector;

    public UserControllerProxy2() {
        this.metricsCollector = new MetricsCollector();
    }

    public String login(String username, String password) {
        String res = super.login(username, password);
        metricsCollector.record("requestInfo");
        return res;
    }

    public String register(String telephone, String password) {
        String res = super.register(telephone, password);
        metricsCollector.record("requestInfo");
        return res;
    }

}
