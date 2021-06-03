package com.xuxiaojin.observer.guavademo;

import com.google.common.eventbus.Subscribe;

public class RegPromotionObserver {

    @Subscribe
    public void handleRegSuccess(Long userId) {
        System.out.println("给 " + userId + "发优惠劵");
    }
}
