package com.xuxiaojin.proxy.aopdemo.advisedapo;

import java.util.Optional;

public interface Account {
    Optional<Double> getBalance();
    boolean isSuspended();
}
