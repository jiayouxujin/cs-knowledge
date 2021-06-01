package com.xuxiaojin.singleton;

public class SingletonDoubleCheck {
    private volatile static SingletonDoubleCheck singletonDoubleCheck;

    private SingletonDoubleCheck() {
    }

    public static SingletonDoubleCheck getInstance() {
        if (singletonDoubleCheck == null) {
            synchronized (SingletonDoubleCheck.class) {
                if (singletonDoubleCheck == null) {
                    singletonDoubleCheck = new SingletonDoubleCheck();
                }
            }
        }
        return singletonDoubleCheck;
    }
}

