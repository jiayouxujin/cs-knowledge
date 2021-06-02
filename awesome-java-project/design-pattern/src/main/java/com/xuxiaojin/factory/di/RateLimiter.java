package com.xuxiaojin.factory.di;

public class RateLimiter {
    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter r) {
        this.redisCounter = r;
    }

    public boolean isValid(){
        this.redisCounter.increamentAndGet();
        return true;
    }
}
