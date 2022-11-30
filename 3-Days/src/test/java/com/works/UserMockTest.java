package com.works;


import com.works.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.condition.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class UserMockTest {

    @InjectMocks
    UserService userService;

    @InjectMocks
    Action action;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userEmailTest() {
        int count = userService.userEmail("ali@mail.com");
        System.out.println("Count :" + count);
    }

    @Test
    public void userJwtTest() {
        userService.jwt("kminchelle", "0lelplR");
    }

    @Test(expected = Exception.class)
    public void userJWTErrorTest() {
        userService.jwt("kminchelle", "0lelplR1");
    }

    @Test
    public void userAddCustomer() {
        boolean status = userService.addCustomer(4000, "iPhonex"+Math.random());
        Assert.assertTrue(status);
    }

    @Test
    @Ignore
    public void ignoreTest() {
        System.out.println("ignoreTest Call");
    }

    @Test
    public void repeatedTest() {
        System.out.println("repeatedTest call");
    }

    @Test
    @EnabledOnJre(value = {JRE.JAVA_8})
    public void enabledOnJre() {
        System.out.println("enabledOnJre Call");
        Assert.assertTrue(true);
    }

    @Test
    @DisabledOnJre(value = JRE.JAVA_8)
    public void disabledOnJre() {
        System.out.println("disabledOnJre Call");
        Assert.assertTrue(false);
    }

    @Test
    @EnabledOnOs(value = OS.WINDOWS)
    public void windows() {
        System.out.println("WINDOWS Call");
    }

    @Test
    @EnabledOnOs(value = OS.MAC)
    public void mac() {
        System.out.println("MAC Call");
    }


    @Test
    public void threadTest() throws InterruptedException {
        boolean status = action.call();
        Assert.assertTrue(status);
    }

}
