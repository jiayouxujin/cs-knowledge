package com.xuxiaojin.proxy.aopdemo;


import org.springframework.aop.framework.ProxyFactory;

import java.net.Proxy;

public class ProxifiedCall {
    public static void main(String[] args) {
        ProxyFactory proxyFactory=new ProxyFactory(new AccountImpl());
        proxyFactory.addInterface(Account.class);

        Account account= (Account) proxyFactory.getProxy();
        System.out.println(String.format("Object Class: [%s] ",account.getClass().getCanonicalName()));
//        System.out.println(String.format("Account balance: [ %.2f $]",account.getBalance()));
    }
}
