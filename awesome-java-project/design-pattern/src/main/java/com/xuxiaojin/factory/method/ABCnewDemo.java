package com.xuxiaojin.factory.method;

public class ABCnewDemo {
    public static void main(String[] args) {
        AFactory aFactory = ABCFactory.create("B");
        A a = aFactory.create();
    }
}
