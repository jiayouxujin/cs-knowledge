package com.xuxiaojin.principle.polymorphism.interfacecase;

public class Demo {

    private static void print(Iterator iterator) {
        System.out.println(iterator.next());
        System.out.println(iterator.remove());
    }

    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);

        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }
}
