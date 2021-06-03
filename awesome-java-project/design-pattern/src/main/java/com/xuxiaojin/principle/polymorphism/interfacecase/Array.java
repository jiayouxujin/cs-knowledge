package com.xuxiaojin.principle.polymorphism.interfacecase;

public class Array implements Iterator {
    private String[] data;

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return "array next";
    }

    @Override
    public String remove() {
        return "array remove";
    }
}
