package com.xuxiaojin.singleton;

public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass() {
    }

    private static class SingletonHolder {
        private static SingletonStaticInnerClass singletonStaticInnerClass = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.singletonStaticInnerClass;
    }

    public static void main(String[] args) {
        SingletonStaticInnerClass.getInstance();
    }
}
