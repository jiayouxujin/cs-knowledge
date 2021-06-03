package com.xuxiaojin.observer.userdemo;

public class RegNotificationObserver implements RegObserver {
    @Override
    public void handleRegSuccess(long userId) {
        System.out.println("welcome " + userId);
    }
}
