package com.xuxiaojin.principle.polymorphism.interfacecase;

public class LinkedList implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return "linked list next";
    }

    @Override
    public String remove() {
        return "linked list remove";
    }
}
