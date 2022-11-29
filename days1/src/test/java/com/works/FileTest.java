package com.works;

import com.works.utils.FileControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.TestPropertySource;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(value = "classpath:application.properties")
public class FileTest {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    FileControl fileControl;
    @BeforeAll
    public void beforeAll() {
        fileControl = new FileControl();
    }

    @Test
    public void fileCreateTest() {
        boolean status = fileControl.fileCreate();
        Assertions.assertTrue(status);
    }

    @Test
    public void dbTest() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        Connection con = dataSource.getConnection();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(con),
                () -> Assertions.assertTrue( !con.isClosed() )
        );
    }

}
