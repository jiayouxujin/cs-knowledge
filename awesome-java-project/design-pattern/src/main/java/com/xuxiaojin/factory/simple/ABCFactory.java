package com.xuxiaojin.factory.simple;

public class ABCFactory {
    public static A create(String type) {
        A a = null;
        if (type.equals("B")) {
            a = new B();
        } else if (type.equals("C")) {
            a = new C();
        }
        return a;
    }
}
