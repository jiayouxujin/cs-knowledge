package com.xuxiaojin.observer.userdemo;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    List<RegObserver> regObservers;

    public UserController() {
        this.regObservers = new ArrayList<>();
    }

    public void setRegObservers(RegObserver observers) {
        regObservers.add(observers);
    }

    public void register(String telephone, String password) {
        System.out.println("register success");
        long userId = 1L;
        for (RegObserver observer : regObservers) {
            observer.handleRegSuccess(userId);
        }
    }
}
