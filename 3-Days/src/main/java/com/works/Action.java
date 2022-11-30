package com.works;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Action {

    //ExecutorService executorService = Executors.newFixedThreadPool(3);

    public boolean call() throws InterruptedException {

        Runnable rn1 =  () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Image-1 Success");
            }catch (Exception ex) {}
        };
        Thread th1 = new Thread(rn1);
        th1.start();
        th1.join();


        Runnable rn2 = () -> {
            try {
                Thread.sleep(2000);
                System.out.println("Image-2 Success");
            }catch (Exception ex) {}
        };
        Thread th2 = new Thread(rn2);
        th2.start();
        th2.join();

        System.out.println("All Thread Finish");
        return true;

    }


}
