package com.xuxiaojin;

public class FirstWorker extends Thread {
    public void run() {
        long sum = 0;
        for (int i = 0; i < 10000000; i++) {
            sum += i;

            if (i % 1000000 == 0) {
                Thread running = Thread.currentThread();
                System.out.println(running.getName() + " " + i);
            }
        }
    }

    public static void main(String[] args) {
        FirstWorker a = new FirstWorker();
        FirstWorker b = new FirstWorker();

        System.out.println("Starting...");
        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("All done");
    }
}
