package com.xuxiaojin;

public class MultiSynch {
    private int apple, banana;
    private Object fruitLock;

    private int[] nums;
    private int numLen;
    private Object numLock;

    public MultiSynch() {
        apple = 0;
        banana = 0;
        fruitLock = new Object();

        numLen = 0;
        numLock = new Object();
    }

    public void addFruit() {
        synchronized (fruitLock) {
            apple++;
            banana++;
        }
    }

    public int getFruit() {
        synchronized (fruitLock) {
            return (apple + banana);
        }
    }

    public void pushNum(int num) {
        synchronized (numLock) {
            nums[numLen] = num;
            numLen++;
        }
    }

    public int popNum() {
        int result;
        synchronized (numLock) {
            result = nums[numLen - 1];
            numLen--;
        }
        if (result < 0) result = -1 * result;
        return result;
    }

    public void both() {
        synchronized (fruitLock) {
            synchronized (numLock) {
                //只要获取锁的顺序一致能够避免死锁现象
            }
        }
    }
}
