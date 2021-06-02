package com.xuxiaojin.proxy.aopdemo;

public class DirectCall {
    public static void main(String[] args) {
        Account account=new AccountImpl();
        System.out.println(String.format("Object Class: [%s] ",account.getClass().getCanonicalName()));
//        System.out.println(String.format("Account balance: [ %.2f $]",account.getBalance()));
    }
}
