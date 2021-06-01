package com.xuxiaojin.singleton.more;

import java.util.concurrent.ConcurrentHashMap;

public class SingletonThread {
    private static ConcurrentHashMap<Long, SingletonThread> map = new ConcurrentHashMap<>();

    private SingletonThread() {
    }

    public static SingletonThread getInstance() {
        Long threadId = Thread.currentThread().getId();
        map.putIfAbsent(threadId, new SingletonThread());
        return map.get(threadId);
    }

}
