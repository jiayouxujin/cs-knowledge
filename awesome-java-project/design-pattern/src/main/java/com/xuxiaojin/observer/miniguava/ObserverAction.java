package com.xuxiaojin.observer.miniguava;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event){
        try {
            method.invoke(target,event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
