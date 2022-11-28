package com.works;

import com.works.utils.Action;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ActionTest {

    Action action = null;

    @BeforeAll
    public void beforeAll() {
        action = new Action();
        System.out.println("beforeAll Call");
    }

    public ActionTest() {
        System.out.println("ActionTest Call");
    }

    int i = 0;
    @BeforeEach
    public void beforeEach() {
        i++;
        System.out.println("beforeEach Call");
    }

    @Order(1)
    @Test
    @DisplayName("Action Sum Test")
    public void sumTest() {
        System.out.println(action.hashCode());
        long start = System.currentTimeMillis();
        int sm = action.sum(30,50);
        long end = System.currentTimeMillis();
        long between = end - start;
        System.out.println("sum between :" + between );
        System.out.println("i : " + i);
        Assertions.assertEquals(90, sm);
    }

    @Order(2)
    @Test
    @Disabled
    public void testOne() {
        System.out.println(action.hashCode());
        System.out.println("testOne Call");
        System.out.println("i : " + i);
    }

    @Order(3)
    @Test
    @Timeout(value = 1950, unit = TimeUnit.MILLISECONDS)
    public void dataTest() {
        int chars = action.data("spring");
        Assertions.assertEquals(6, chars);
    }

    int x = 0;
    @Order(4)
    @RepeatedTest(5)
    public void call() {
        x++;
        System.out.println("x: " + x);
    }

    @Order(5)
    @ParameterizedTest
    @ValueSource(strings = {"İstanbul", "Ankara", "Adana", "Antalya"})
    public void citiesTest( String city ) {
        try {
            Thread.sleep(2000);
        }catch (Exception ex) {}
        System.out.println(city);
    }

    @Order(6)
    @ParameterizedTest
    @CsvSource(value = { "ali@mail.com,12345", "veli@mail.com,12345" })
    public void multiParams( String userName, int password ) {
        System.out.println( userName +" "+ password );
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach Call");
        i = 0;
    }

    @AfterAll
    public void afterAll() {
        System.out.println("afterAll Call");
        action = null;
    }

}
