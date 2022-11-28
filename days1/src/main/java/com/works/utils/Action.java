package com.works.utils;

public class Action {

    public int sum(int num1, int num2) {
        for (int i = 0; i < 100000; i++) {}
        return num1 + num2;
    }

    public int data( String data ) {
        try {
            Thread.sleep(2000);
        }catch (Exception ex) {}
        return data.length();
    }

}
