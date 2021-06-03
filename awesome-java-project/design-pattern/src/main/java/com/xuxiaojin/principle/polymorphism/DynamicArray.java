package com.xuxiaojin.principle.polymorphism;


public class DynamicArray {
    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size() {
        return this.size;
    }

    public int get(int i) {
        if (i >= 0 && i < size) {
            return elements[i];
        }
        throw new IllegalArgumentException("Index需要在0~size之间");
    }

    public void add(Integer e) {
        ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity() {
        if (size > capacity) {
            this.capacity = this.capacity * 2;
            Integer[] copy = new Integer[capacity];
            System.arraycopy(elements, 0, copy, 0, this.size);
            this.elements = copy;
        }
    }
}
