package com.xuxiaojin.factory.method;

public class BFactory implements AFactory{
    @Override
    public A create() {
        return new B();
    }
}
