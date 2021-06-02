package com.xuxiaojin.proxy.aopdemo.advisedapo;

import org.springframework.aop.framework.ProxyFactory;

import java.util.Optional;

public class AdvisedProxifiedCall {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new AccountImpl());
        factory.addInterface(Account.class);

        factory.addAdvice(new TracingAdvice());
        Account account = (Account) factory.getProxy();
        System.out.println(String.format("Object Class : [ %s ]", account.getClass().getCanonicalName()));
        System.out.println(String.format("Is account suspended: [ %b ]", account.isSuspended()));
        Optional<Double> balance = account.getBalance();
        System.out.println(balance.isPresent() ? balanceMessage(balance.get()) : "Unavailable balance");
    }

    private static String balanceMessage(Double balance) {
        return String.format("Account balance: [ %.2f € ]", balance);
    }
}
