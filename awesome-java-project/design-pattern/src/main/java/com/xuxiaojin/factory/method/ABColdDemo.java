package com.xuxiaojin.factory.method;

public class ABColdDemo {

    public static void main(String[] args) {
        AFactory aFactory = null;
        String type = "B";
        if (type.equals("B")) {
            aFactory = new BFactory();
        } else {
            aFactory = new CFactory();
        }

        A a = aFactory.create();
    }
}
