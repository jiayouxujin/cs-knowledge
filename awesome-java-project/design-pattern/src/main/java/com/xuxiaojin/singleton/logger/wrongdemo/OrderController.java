package com.xuxiaojin.singleton.logger.wrongdemo;

import com.xuxiaojin.singleton.logger.Logger;

public class OrderController {
    private Logger logger = new Logger();

    public void order(String message) {
        logger.log(message);
    }
}
