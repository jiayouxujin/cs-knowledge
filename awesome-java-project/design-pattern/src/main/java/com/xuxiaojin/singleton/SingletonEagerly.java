package com.xuxiaojin.singleton;

public class SingletonEagerly {

    private static SingletonEagerly singletonEagerly = new SingletonEagerly();

    private SingletonEagerly() {

    }

    public static SingletonEagerly getInstance() {
        return singletonEagerly;
    }
}
