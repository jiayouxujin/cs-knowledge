package com.xuxiaojin;

import java.util.concurrent.Semaphore;

public class AddRemove {
    private int len = 0;
    private Semaphore canRemove;
    private static final int COUNT = 100;

    public AddRemove() {
        len = 0;
        canRemove = new Semaphore(0);
    }

    public synchronized void add() {
        len++;
        System.out.println(Thread.currentThread().getName() + " add " + (len - 1));
        canRemove.release();
    }

    public void remove() {
        try {
            canRemove.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " remove " + (len - 1));
            len--;
        }
    }

    private class Adder extends Thread {
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                add();
                Thread.yield();
            }
        }
    }

    private class Remover extends Thread {
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                remove();
                Thread.yield();
            }
            System.out.println(getName() + " done");
        }
    }

    public void demo() {
        Thread a1 = new Adder();
        Thread a2 = new Adder();

        Thread t1 = new Remover();
        Thread t2 = new Remover();

        a1.start();
        a2.start();
        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new AddRemove().demo();
    }
}
