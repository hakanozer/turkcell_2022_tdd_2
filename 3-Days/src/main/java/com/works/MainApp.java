package com.works;

import com.works.services.UserService;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {

        //UserService userService = new UserService();
        //userService.addCustomer(4000, "iphonex");

        Action action = new Action();
        boolean status = action.call();
        System.out.println("status : " + status);
    }
}
