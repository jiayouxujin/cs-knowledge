package com.xuxiaojin.observer.userdemo;

public class user {

    public static void main(String[] args) {
        UserController userController = new UserController();
        RegPromotionObserver regPromotionObserver = new RegPromotionObserver();
        RegNotificationObserver regNotificationObserver = new RegNotificationObserver();

        userController.setRegObservers(regNotificationObserver);
        userController.setRegObservers(regPromotionObserver);

        userController.register("1111", "222");
    }
}
