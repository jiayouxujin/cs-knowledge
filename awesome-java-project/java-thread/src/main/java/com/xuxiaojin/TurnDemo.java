package com.xuxiaojin;

import java.util.concurrent.Semaphore;

public class TurnDemo {
    Semaphore aGo = new Semaphore(1);
    Semaphore bGo = new Semaphore(2);

    void a() {
        try {
            aGo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("It's A turn,A rules!");
        bGo.release();
    }

    void b() {
        try {
            bGo.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("It's B turn,B rules!");
        aGo.release();
    }

    public void demo() {
        final Semaphore finished = new Semaphore(-1);

        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    b();
                }
                finished.release();
            }
        }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    a();
                }
                finished.release();
            }
        }.start();

        try{
            finished.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all done");
    }

    public static void main(String[] args) {
        new TurnDemo().demo();
    }
}
