package com.xuxiaojin.observer.guavademo;

import java.util.ArrayList;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        List<Object> observers = new ArrayList<>();
        RegPromotionObserver regPromotionObserver = new RegPromotionObserver();
        RegNotificationObserver regNotificationObserver = new RegNotificationObserver();

        observers.add(regPromotionObserver);
        observers.add(regNotificationObserver);

        UserController userController=new UserController();
        userController.setRegObservers(observers);

        userController.register("111","222");
    }
}
