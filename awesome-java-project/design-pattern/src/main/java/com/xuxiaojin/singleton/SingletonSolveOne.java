package com.xuxiaojin.singleton;

public class SingletonSolveOne {

    private static SingletonSolveOne singletonSolveOne;

    private SingletonSolveOne() {
    }

    public static synchronized SingletonSolveOne getInstance() {
        if (singletonSolveOne == null) {
            singletonSolveOne = new SingletonSolveOne();
        }
        return singletonSolveOne;
    }
}
