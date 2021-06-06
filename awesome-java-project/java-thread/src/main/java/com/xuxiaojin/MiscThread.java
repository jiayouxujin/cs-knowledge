package com.xuxiaojin;

public class MiscThread {

    public static void whoami() {
        Thread runningMe = Thread.currentThread();
        System.out.println("whoami thread: " + runningMe.getName());
    }

    public static class MiscWorker extends Thread {
        public void run() {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MiscThread.whoami();
        }
    }

    public static void main(String[] args) {
        MiscThread.whoami();
        Thread worker = new MiscWorker();
        worker.run();   //永远不要这么做
        worker.start();
        System.out.println("all done");
    }
}
