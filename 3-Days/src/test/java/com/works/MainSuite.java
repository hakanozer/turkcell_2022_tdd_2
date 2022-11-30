package com.works;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

public class MainSuite {
    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(AppSuiteTest.class);
        List<Failure> failures = result.getFailures();
        for( Failure item : failures ) {
            String message = item.getMessage();
            System.out.println(message);
        }
        System.out.println( "FailureCount :" + result.getFailures().size() );
        System.out.println( "getIgnoreCount :" + result.getIgnoreCount());
        System.out.println( "wasSuccessful :" + result.wasSuccessful());
        System.out.println( "getRunCount :" + result.getRunCount());

    }
}
