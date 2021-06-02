package com.xuxiaojin.proxy.aopdemo.advisedapo;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class TracingAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(String.format("Calling method [ %s ]",method.getName()));
    }
}
