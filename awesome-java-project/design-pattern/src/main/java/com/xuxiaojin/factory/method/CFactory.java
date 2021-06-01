package com.xuxiaojin.factory.method;

public class CFactory implements AFactory{
    @Override
    public A create() {
        return new C();
    }
}
