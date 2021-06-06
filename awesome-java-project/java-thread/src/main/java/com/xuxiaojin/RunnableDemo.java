package com.xuxiaojin;

public class RunnableDemo implements Runnable {
    @Override
    public void run() {
        System.out.println("Yay Runnable!");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableDemo());
        thread.start();
    }
}
