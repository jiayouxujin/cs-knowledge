package com.xuxiaojin.observer.userdemo;

public class RegPromotionObserver implements RegObserver {
    @Override
    public void handleRegSuccess(long userId) {
        System.out.println("给 " + userId + "发放优惠券");
    }
}
