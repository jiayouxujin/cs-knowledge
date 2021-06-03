package com.xuxiaojin.observer.guavademo;

import com.google.common.eventbus.Subscribe;

public class RegNotificationObserver {


    @Subscribe
    public void testParameter(String username, Long userId) {
        System.out.println("hello world");
    }
}
