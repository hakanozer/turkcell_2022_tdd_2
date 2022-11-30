package com.works;

import com.works.utils.Util;
import org.junit.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    static Random random;
    static Util util;
    public AppTest() {
        System.out.println("AppTest Call");
    }

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass call");
        random = new Random();
        util = new Util();
    }

    @Before
    public void before() {
        System.out.println("before call");
    }

    @Test()
    public void appTest1() {
        System.out.println("appTest1 call :" + random.nextInt(100));
    }

    @Test
    public void appTest2() {
        System.out.println("appTest2 call :" + random.nextInt(100));
    }


    @Order(2)
    @Test
    public void dbCloseTest() throws SQLException {
        Connection con = util.con();
        Assert.assertNotNull(con);
        Assert.assertTrue(!con.isClosed());

        util.conn.close();
        Assert.assertTrue(util.close());
    }


    @After
    public void after() {
        System.out.println("after call");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass Call");
    }

}
