package com.xuxiaojin;

public class Pair {
    private int a, b;

    public Pair() {
        a = 0;
        b = 0;
    }

    public String sum() {
        return String.valueOf(a + b);
    }

    public void inc() {
        a++;
        b++;
    }


    public static void main(String[] args) {
        Pair pair = new Pair();
        Thread sumThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf(pair.sum());
            }
        });

        Thread inc = new Thread(new Runnable() {
            @Override
            public void run() {
                pair.inc();
            }
        });

        sumThread.start();
        inc.start();
    }
}
