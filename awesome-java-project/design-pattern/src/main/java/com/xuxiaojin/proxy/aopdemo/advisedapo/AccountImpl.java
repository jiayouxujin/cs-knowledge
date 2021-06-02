package com.xuxiaojin.proxy.aopdemo.advisedapo;

import java.util.Optional;

public class AccountImpl implements Account {
    @Override
    public Optional<Double> getBalance() {
        if (isSuspended()) {
            return Optional.empty();
        }
        return Optional.of(100.50);
    }

    @Override
    public boolean isSuspended() {
        return true;
    }
}
